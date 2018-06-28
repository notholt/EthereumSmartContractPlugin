package com.notholt.ethereumsmartcontractplugin;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class Conference extends Contract {
    private static final String BINARY = "{\r\n"
            + "\t\"linkReferences\": {},\r\n"
            + "\t\"object\": \"608060405234801561001057600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060646003819055506000600281905550610648806100706000396000f30060806040526004361061008e576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806313381fbf1461009357806361203265146100ea578063705099b91461014157806383197ef01461018e578063a977c71e146101a5578063cebe09c9146101d2578063ec3a6f73146101fd578063edca914c14610228575b600080fd5b34801561009f57600080fd5b506100d4600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061023f565b6040518082815260200191505060405180910390f35b3480156100f657600080fd5b506100ff610257565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34801561014d57600080fd5b5061018c600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291908035906020019092919050505061027c565b005b34801561019a57600080fd5b506101a3610444565b005b3480156101b157600080fd5b506101d0600480360381019080803590602001909291905050506104d5565b005b3480156101de57600080fd5b506101e761053b565b6040518082815260200191505060405180910390f35b34801561020957600080fd5b50610212610541565b6040518082815260200191505060405180910390f35b34801561023457600080fd5b5061023d610547565b005b60016020528060005260406000206000915090505481565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b60008060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415156102d95761043f565b81600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054141561043e57309050818173ffffffffffffffffffffffffffffffffffffffff163110151561043d578273ffffffffffffffffffffffffffffffffffffffff166108fc839081150290604051600060405180830381858888f19350505050507fbb28353e4598c3b9199101a66e0989549b659a59a54d2c27fbb183f1932c8e6d8383604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390a16000600160008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550600260008154809291906001900391905055505b5b5b505050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614156104d3576000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561053057610538565b806003819055505b50565b60035481565b60025481565b60035460025410151561055957600080fd5b34600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055506002600081548092919060010191905055507fe1fffcc4923d04b559f4d29a8bfc6cda04eb5b0d3c460751c2402c5c5cc9109c3334604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390a15600a165627a7a723058209cf50ddc2387354865c64e9e251c676a14dbf50262963340eed7958903be49aa0029\",\r\n"
            + "\t\"opcodes\": \"PUSH1 0x80 PUSH1 0x40 MSTORE CALLVALUE DUP1 ISZERO PUSH2 0x10 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP CALLER PUSH1 0x0 DUP1 PUSH2 0x100 EXP DUP2 SLOAD DUP2 PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF MUL NOT AND SWAP1 DUP4 PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND MUL OR SWAP1 SSTORE POP PUSH1 0x64 PUSH1 0x3 DUP2 SWAP1 SSTORE POP PUSH1 0x0 PUSH1 0x2 DUP2 SWAP1 SSTORE POP PUSH2 0x648 DUP1 PUSH2 0x70 PUSH1 0x0 CODECOPY PUSH1 0x0 RETURN STOP PUSH1 0x80 PUSH1 0x40 MSTORE PUSH1 0x4 CALLDATASIZE LT PUSH2 0x8E JUMPI PUSH1 0x0 CALLDATALOAD PUSH29 0x100000000000000000000000000000000000000000000000000000000 SWAP1 DIV PUSH4 0xFFFFFFFF AND DUP1 PUSH4 0x13381FBF EQ PUSH2 0x93 JUMPI DUP1 PUSH4 0x61203265 EQ PUSH2 0xEA JUMPI DUP1 PUSH4 0x705099B9 EQ PUSH2 0x141 JUMPI DUP1 PUSH4 0x83197EF0 EQ PUSH2 0x18E JUMPI DUP1 PUSH4 0xA977C71E EQ PUSH2 0x1A5 JUMPI DUP1 PUSH4 0xCEBE09C9 EQ PUSH2 0x1D2 JUMPI DUP1 PUSH4 0xEC3A6F73 EQ PUSH2 0x1FD JUMPI DUP1 PUSH4 0xEDCA914C EQ PUSH2 0x228 JUMPI JUMPDEST PUSH1 0x0 DUP1 REVERT JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x9F JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0xD4 PUSH1 0x4 DUP1 CALLDATASIZE SUB DUP2 ADD SWAP1 DUP1 DUP1 CALLDATALOAD PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND SWAP1 PUSH1 0x20 ADD SWAP1 SWAP3 SWAP2 SWAP1 POP POP POP PUSH2 0x23F JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 DUP3 DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0xF6 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0xFF PUSH2 0x257 JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 DUP3 PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x14D JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x18C PUSH1 0x4 DUP1 CALLDATASIZE SUB DUP2 ADD SWAP1 DUP1 DUP1 CALLDATALOAD PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND SWAP1 PUSH1 0x20 ADD SWAP1 SWAP3 SWAP2 SWAP1 DUP1 CALLDATALOAD SWAP1 PUSH1 0x20 ADD SWAP1 SWAP3 SWAP2 SWAP1 POP POP POP PUSH2 0x27C JUMP JUMPDEST STOP JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x19A JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x1A3 PUSH2 0x444 JUMP JUMPDEST STOP JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x1B1 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x1D0 PUSH1 0x4 DUP1 CALLDATASIZE SUB DUP2 ADD SWAP1 DUP1 DUP1 CALLDATALOAD SWAP1 PUSH1 0x20 ADD SWAP1 SWAP3 SWAP2 SWAP1 POP POP POP PUSH2 0x4D5 JUMP JUMPDEST STOP JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x1DE JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x1E7 PUSH2 0x53B JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 DUP3 DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x209 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x212 PUSH2 0x541 JUMP JUMPDEST PUSH1 0x40 MLOAD DUP1 DUP3 DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x234 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x23D PUSH2 0x547 JUMP JUMPDEST STOP JUMPDEST PUSH1 0x1 PUSH1 0x20 MSTORE DUP1 PUSH1 0x0 MSTORE PUSH1 0x40 PUSH1 0x0 KECCAK256 PUSH1 0x0 SWAP2 POP SWAP1 POP SLOAD DUP2 JUMP JUMPDEST PUSH1 0x0 DUP1 SWAP1 SLOAD SWAP1 PUSH2 0x100 EXP SWAP1 DIV PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND DUP2 JUMP JUMPDEST PUSH1 0x0 DUP1 PUSH1 0x0 SWAP1 SLOAD SWAP1 PUSH2 0x100 EXP SWAP1 DIV PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND CALLER PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND EQ ISZERO ISZERO PUSH2 0x2D9 JUMPI PUSH2 0x43F JUMP JUMPDEST DUP2 PUSH1 0x1 PUSH1 0x0 DUP6 PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND DUP2 MSTORE PUSH1 0x20 ADD SWAP1 DUP2 MSTORE PUSH1 0x20 ADD PUSH1 0x0 KECCAK256 SLOAD EQ ISZERO PUSH2 0x43E JUMPI ADDRESS SWAP1 POP DUP2 DUP2 PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND BALANCE LT ISZERO ISZERO PUSH2 0x43D JUMPI DUP3 PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND PUSH2 0x8FC DUP4 SWAP1 DUP2 ISZERO MUL SWAP1 PUSH1 0x40 MLOAD PUSH1 0x0 PUSH1 0x40 MLOAD DUP1 DUP4 SUB DUP2 DUP6 DUP9 DUP9 CALL SWAP4 POP POP POP POP POP PUSH32 0xBB28353E4598C3B9199101A66E0989549B659A59A54D2C27FBB183F1932C8E6D DUP4 DUP4 PUSH1 0x40 MLOAD DUP1 DUP4 PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND DUP2 MSTORE PUSH1 0x20 ADD DUP3 DUP2 MSTORE PUSH1 0x20 ADD SWAP3 POP POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 LOG1 PUSH1 0x0 PUSH1 0x1 PUSH1 0x0 DUP6 PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND DUP2 MSTORE PUSH1 0x20 ADD SWAP1 DUP2 MSTORE PUSH1 0x20 ADD PUSH1 0x0 KECCAK256 DUP2 SWAP1 SSTORE POP PUSH1 0x2 PUSH1 0x0 DUP2 SLOAD DUP1 SWAP3 SWAP2 SWAP1 PUSH1 0x1 SWAP1 SUB SWAP2 SWAP1 POP SSTORE POP JUMPDEST JUMPDEST JUMPDEST POP POP POP JUMP JUMPDEST PUSH1 0x0 DUP1 SWAP1 SLOAD SWAP1 PUSH2 0x100 EXP SWAP1 DIV PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND CALLER PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND EQ ISZERO PUSH2 0x4D3 JUMPI PUSH1 0x0 DUP1 SWAP1 SLOAD SWAP1 PUSH2 0x100 EXP SWAP1 DIV PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND SELFDESTRUCT JUMPDEST JUMP JUMPDEST PUSH1 0x0 DUP1 SWAP1 SLOAD SWAP1 PUSH2 0x100 EXP SWAP1 DIV PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND CALLER PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND EQ ISZERO ISZERO PUSH2 0x530 JUMPI PUSH2 0x538 JUMP JUMPDEST DUP1 PUSH1 0x3 DUP2 SWAP1 SSTORE POP JUMPDEST POP JUMP JUMPDEST PUSH1 0x3 SLOAD DUP2 JUMP JUMPDEST PUSH1 0x2 SLOAD DUP2 JUMP JUMPDEST PUSH1 0x3 SLOAD PUSH1 0x2 SLOAD LT ISZERO ISZERO PUSH2 0x559 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST CALLVALUE PUSH1 0x1 PUSH1 0x0 CALLER PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND DUP2 MSTORE PUSH1 0x20 ADD SWAP1 DUP2 MSTORE PUSH1 0x20 ADD PUSH1 0x0 KECCAK256 DUP2 SWAP1 SSTORE POP PUSH1 0x2 PUSH1 0x0 DUP2 SLOAD DUP1 SWAP3 SWAP2 SWAP1 PUSH1 0x1 ADD SWAP2 SWAP1 POP SSTORE POP PUSH32 0xE1FFFCC4923D04B559F4D29A8BFC6CDA04EB5B0D3C460751C2402C5C5CC9109C CALLER CALLVALUE PUSH1 0x40 MLOAD DUP1 DUP4 PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND PUSH20 0xFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF AND DUP2 MSTORE PUSH1 0x20 ADD DUP3 DUP2 MSTORE PUSH1 0x20 ADD SWAP3 POP POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 LOG1 JUMP STOP LOG1 PUSH6 0x627A7A723058 KECCAK256 SWAP13 0xf5 0xd 0xdc 0x23 DUP8 CALLDATALOAD 0x48 PUSH6 0xC64E9E251C67 PUSH11 0x14DBF50262963340EED795 DUP10 SUB 0xbe 0x49 0xaa STOP 0x29 \",\r\n"
            + "\t\"sourceMap\": \"0:1296:0:-;;;359:91;8:9:-1;5:2;;;30:1;27;20:12;5:2;359:91:0;397:10;385:9;;:22;;;;;;;;;;;;;;;;;;421:3;413:5;:11;;;;445:1;428:14;:18;;;;0:1296;;;;;;\"\r\n"
            + "}";

    public static final String FUNC_REGISTRANTSPAID = "registrantsPaid";

    public static final String FUNC_ORGANIZER = "organizer";

    public static final String FUNC_REFUNDTICKET = "refundTicket";

    public static final String FUNC_DESTROY = "destroy";

    public static final String FUNC_CHANGEQUOTA = "changeQuota";

    public static final String FUNC_QUOTA = "quota";

    public static final String FUNC_NUMREGISTRANTS = "numRegistrants";

    public static final String FUNC_BUYTICKET = "buyTicket";

    public static final Event DEPOSIT_EVENT = new Event("Deposit", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event REFUND_EVENT = new Event("Refund", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    protected Conference(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Conference(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<BigInteger> registrantsPaid(String param0) {
        final Function function = new Function(FUNC_REGISTRANTSPAID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> organizer() {
        final Function function = new Function(FUNC_ORGANIZER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> refundTicket(String recipient, BigInteger amount) {
        final Function function = new Function(
                FUNC_REFUNDTICKET, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(recipient), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> destroy() {
        final Function function = new Function(
                FUNC_DESTROY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> changeQuota(BigInteger newquota) {
        final Function function = new Function(
                FUNC_CHANGEQUOTA, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(newquota)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> quota() {
        final Function function = new Function(FUNC_QUOTA, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> numRegistrants() {
        final Function function = new Function(FUNC_NUMREGISTRANTS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> buyTicket() {
        final Function function = new Function(
                FUNC_BUYTICKET, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<Conference> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Conference.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Conference> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Conference.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public List<DepositEventResponse> getDepositEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DEPOSIT_EVENT, transactionReceipt);
        ArrayList<DepositEventResponse> responses = new ArrayList<DepositEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DepositEventResponse typedResponse = new DepositEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._from = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<DepositEventResponse> depositEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, DepositEventResponse>() {
            @Override
            public DepositEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(DEPOSIT_EVENT, log);
                DepositEventResponse typedResponse = new DepositEventResponse();
                typedResponse.log = log;
                typedResponse._from = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<DepositEventResponse> depositEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DEPOSIT_EVENT));
        return depositEventObservable(filter);
    }

    public List<RefundEventResponse> getRefundEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REFUND_EVENT, transactionReceipt);
        ArrayList<RefundEventResponse> responses = new ArrayList<RefundEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RefundEventResponse typedResponse = new RefundEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._to = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<RefundEventResponse> refundEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, RefundEventResponse>() {
            @Override
            public RefundEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(REFUND_EVENT, log);
                RefundEventResponse typedResponse = new RefundEventResponse();
                typedResponse.log = log;
                typedResponse._to = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<RefundEventResponse> refundEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REFUND_EVENT));
        return refundEventObservable(filter);
    }

    public static Conference load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Conference(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Conference load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Conference(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class DepositEventResponse {
        public Log log;

        public String _from;

        public BigInteger _amount;
    }

    public static class RefundEventResponse {
        public Log log;

        public String _to;

        public BigInteger _amount;
    }
}
