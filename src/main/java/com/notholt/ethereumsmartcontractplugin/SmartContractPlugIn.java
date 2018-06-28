/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notholt.ethereumsmartcontractplugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.exceptions.TransactionException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

/**
 *
 * @author notholt
 */
public class SmartContractPlugIn extends TimerTask{

    /*
     These are the class variables
    */
    private DatagramSocket socket; // The Socket for receiveing UDP-Datagrams
    private byte[] buf = new byte[256]; // The Buffer in which the datagram is stored
    private static final int LISTEN_TO = 1810; //Port to listen
    private static final int RCV_SOCKET_TO = 1000; // Timeout for receiving socket
    private static final int TASK_TIME_S = 5;
    
    private String walletPath;
    private String walletPass;
    private String contractAdr;
    
    private int[] dataSet = new int[10]; // The dataset being received
    private UUID currentUID;
    private Credentials credentials;
    
   // static final BigInteger GAS_PRICE = BigInteger.valueOf(220000000);
    //static final BigInteger GAS_LIMIT = BigInteger.valueOf(6700000);
    
    
    // Smart Contract Object
    EnergyBlockchain contract;
    Web3j web3;
    
    
    
    
    public SmartContractPlugIn()
    {
        // Set up Socket
        try
        {
            this.socket = new DatagramSocket(LISTEN_TO); // Opens a UDP port to listen on
            this.socket.setSoTimeout(RCV_SOCKET_TO); // timeout for blocking process
        }catch(SocketException e)
        {
            System.out.println("ERR! Could not open requested port for listening");
        }
        
        Properties prop = new Properties();
	InputStream input = null;
        try
        {
            
        input = new FileInputStream("config.ini");

		// load a properties file
        prop.load(input);

		// get the property value and print it out
	walletPath   =  prop.getProperty("walletPath");
	walletPass   =  prop.getProperty("walletPass");
	contractAdr =  prop.getProperty("contractAdr");
        System.out.println("---CONFIGURATION----");
        System.out.println("WalletPath: " + walletPath);
        System.out.println("WalletPass: " + walletPass);
        System.out.println("Contract: " + contractAdr);
        
        }catch(Exception e)
        {
            System.out.println("Error in file or file not found");
        }
        
        try
        {
            web3  = Web3j.build(new HttpService("http://localhost:8042/"));  // defaults to http://localhost:8545/
            Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
           
            System.out.println(clientVersion);
            
            //File walletPath = new File("./wallet");
            
            //String walletName = WalletUtils.generateFullNewWalletFile("mexico", walletPath);
            //System.out.printf("Path to wallet: %s \n", walletName);
           // Credentials credentials = WalletUtils.loadCredentials("mexico", "./wallet/"+walletName);
            credentials  = WalletUtils.loadCredentials(walletPass, walletPath);
           
            System.out.printf("Account key = %s",credentials.getAddress());
            
            

            //contract = Conference.deploy(web3, credentials, Inbox.GAS_PRICE, Inbox.GAS_LIMIT).send();  // constructor params 
            
            
            //contract = EnergyBlockchain.load("0x37A46d0f3238011108d7eC1D242635877C0604b3", web3, credentials, Inbox.GAS_PRICE, Inbox.GAS_LIMIT);
            
            // TestBlockchain
            contract = EnergyBlockchain.load(contractAdr, web3, credentials, Inbox.GAS_PRICE, Inbox.GAS_LIMIT);
            
            
            //TransactionReceipt transactionReceipt = Transfer.sendFunds(
            //        web3, credentials, "0x0",
            //        BigDecimal.valueOf(1.0), Convert.Unit.ETHER)
            //        .send();
          

        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
    }
    
    /*
    This process runs every x seconds as devised by target application
    */
    @Override
    public void run() {
        // Receive Data until now
        
        System.out.println("Task started: "+new Date()); // Nice as heartbeat
       
        
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        try {
            socket.receive(packet);
            
            // If we received a data packet
            System.out.println("DATA Received!");
            for(int i = 0; i < dataSet.length; i++)
            {
                ByteBuffer value = ByteBuffer.wrap(buf, i*2, 2);
                dataSet[i] = (int)value.getShort();
            }

            System.out.printf("PV:    %d kWh @ %d ct/kWh \n ", dataSet[0], dataSet[1]);
            System.out.printf("CHP:   %d kWh @ %d ct/kWh \n ", dataSet[2], dataSet[3]);
            System.out.printf("Load:  %d kWh @ %d ct/kWh \n ", dataSet[4], dataSet[5]);
            System.out.printf("Bat:   %d kWh @ %d ct/kWh \n ", dataSet[6], dataSet[7]);
            System.out.printf("Grid:  %d kWh @ %d ct/kWh \n ", dataSet[8], dataSet[9]);

            
            currentUID = UUID.randomUUID();
            
            //JSONObject rcvStruct = new JSONObject();

            //JSONArray jsonValues = new JSONArray();

           try
            {
                System.out.println("Sending values to contract");
                contract.energyToken(BigInteger.valueOf(dataSet[0]), BigInteger.valueOf(dataSet[1]), //PV
                                     BigInteger.valueOf(dataSet[4]), BigInteger.valueOf(dataSet[5]), //LOAD
                                     BigInteger.valueOf(dataSet[6]), BigInteger.valueOf(dataSet[7]), //BAT
                                     BigInteger.valueOf(dataSet[8]), BigInteger.valueOf(dataSet[9]), //GRID
                                     BigInteger.valueOf(dataSet[2]), BigInteger.valueOf(dataSet[3])).send(); //CHP
                //String result = contract.getMessage().send().toString();
                //System.out.println(result);
                System.out.println("Executing depositTokens()");
                contract.depositTokens().send();
            }catch(Exception e)
            {
                System.out.println("ERROR:" +e.getMessage());
                e.printStackTrace();
            }
                
            
            
        } catch (IOException ex) {
            
            // If we did#t receive any packet, then just ignore
            
            //Logger.getLogger(BlockchainJsonAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
	
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        
        
        
        // Set up task
        TimerTask timerTask = new SmartContractPlugIn();
        //running timer task as daemon thread
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, TASK_TIME_S*1000);
        System.out.println("TimerTask started");
        //cancel after sometime
        try {
            Thread.sleep(1200000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
        System.out.println("TimerTask cancelled");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
    
}
