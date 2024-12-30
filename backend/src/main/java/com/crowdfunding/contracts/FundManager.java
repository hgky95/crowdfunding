package com.crowdfunding.contracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/hyperledger/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.12.2.
 */
@SuppressWarnings("rawtypes")
public class FundManager extends Contract {
    public static final String BINARY = "608060405234801561000f575f80fd5b506040516115a93803806115a983398101604081905261002e916101a0565b600180556001600160a01b0383166100975760405162461bcd60e51b815260206004820152602160248201527f496e76616c6964206d696c6573746f6e65206d616e61676572206164647265736044820152607360f81b60648201526084015b60405180910390fd5b6001600160a01b0382166100ed5760405162461bcd60e51b815260206004820181905260248201527f496e76616c69642070726f706f73616c206d616e616765722061646472657373604482015260640161008e565b6001600160a01b0381166101435760405162461bcd60e51b815260206004820152601460248201527f496e76616c696420555344432061646472657373000000000000000000000000604482015260640161008e565b600280546001600160a01b039485166001600160a01b0319918216179091556003805493851693821693909317909255600480549190931691161790556101e0565b80516001600160a01b038116811461019b575f80fd5b919050565b5f805f606084860312156101b2575f80fd5b6101bb84610185565b92506101c960208501610185565b91506101d760408501610185565b90509250925092565b6113bc806101ed5f395ff3fe608060405234801561000f575f80fd5b506004361061011c575f3560e01c806361638ed5116100a9578063ad32563d1161006e578063ad32563d1461029f578063c1b31e65146102c6578063d547741f146102d9578063e24ca897146102ec578063ee1554a3146102ff575f80fd5b806361638ed514610223578063751d658c146102365780637916607e1461026357806391d1485414610285578063a217fddf14610298575f80fd5b806312c0cb9d116100ef57806312c0cb9d146101a7578063248a9ca3146101c65780632f2ff15d146101e857806336568abe146101fd5780633e413bee14610210575f80fd5b806301ffc9a71461012057806302f89be214610148578063092b73c6146101735780630b7d662f14610194575b5f80fd5b61013361012e366004610f03565b610329565b60405190151581526020015b60405180910390f35b60035461015b906001600160a01b031681565b6040516001600160a01b03909116815260200161013f565b610186610181366004610f31565b61035f565b60405190815260200161013f565b60025461015b906001600160a01b031681565b6101866101b5366004610f31565b60056020525f908152604090205481565b6101866101d4366004610f31565b5f9081526020819052604090206001015490565b6101fb6101f6366004610f5c565b610455565b005b6101fb61020b366004610f5c565b61047f565b60045461015b906001600160a01b031681565b6101fb610231366004610f8a565b6104b7565b610133610244366004610f8a565b600760209081525f928352604080842090915290825290205460ff1681565b610133610271366004610f31565b60086020525f908152604090205460ff1681565b610133610293366004610f5c565b61079e565b6101865f81565b6101867f794daa56950487582951e8db2fdbcbee68c2223c65641d0aa02a3afc64f9a86f81565b6101866102d4366004610f5c565b6107c6565b6101fb6102e7366004610f5c565b61085f565b6101fb6102fa366004610f8a565b610883565b61018661030d366004610f5c565b600660209081525f928352604080842090915290825290205481565b5f6001600160e01b03198216637965db0b60e01b148061035957506301ffc9a760e01b6001600160e01b03198316145b92915050565b600254604051639986081d60e01b8152600481018390525f9182916001600160a01b0390911690639986081d906024015f60405180830381865afa1580156103a9573d5f803e3d5ffd5b505050506040513d5f823e601f3d908101601f191682016040526103d09190810190611084565b90505f805b8251811015610433575f85815260076020908152604080832084845290915290205460ff161561042b57828181518110610411576104116111cb565b6020026020010151606001518261042891906111f3565b91505b6001016103d5565b505f8481526005602052604090205461044d9082906111f3565b949350505050565b5f8281526020819052604090206001015461046f81610c97565b6104798383610ca4565b50505050565b6001600160a01b03811633146104a85760405163334bd91960e11b815260040160405180910390fd5b6104b28282610d33565b505050565b6104bf610d9c565b5f811161050c5760405162461bcd60e51b81526020600482015260166024820152754d757374206465706f73697420736f6d65205553444360501b60448201526064015b60405180910390fd5b5f8281526008602052604090205460ff161561055f5760405162461bcd60e51b8152602060048201526012602482015271141c9bda9958dd081a5cc81cdd1bdc1c195960721b6044820152606401610503565b6003546040516318feeb1560e31b8152600481018490525f916001600160a01b03169063c7f758a8906024015f60405180830381865afa1580156105a5573d5f803e3d5ffd5b505050506040513d5f823e601f3d908101601f191682016040526105cc9190810190611224565b905060018160a0015160028111156105e6576105e6611312565b1461062f5760405162461bcd60e51b8152602060048201526019602482015278141c9bdc1bdcd85b081b5d5cdd08189948185c1c1c9bdd9959603a1b6044820152606401610503565b60048054604051636eb1769f60e11b8152339281019290925230602483015283916001600160a01b039091169063dd62ed3e90604401602060405180830381865afa158015610680573d5f803e3d5ffd5b505050506040513d601f19601f820116820180604052508101906106a49190611326565b10156106f25760405162461bcd60e51b815260206004820152601b60248201527f496e73756666696369656e74205553444320616c6c6f77616e636500000000006044820152606401610503565b60045461070a906001600160a01b0316333085610dc6565b5f83815260056020526040812080548492906107279084906111f3565b90915550505f838152600660209081526040808320338452909152812080548492906107549084906111f3565b9091555050604051828152339084907fe030e47e27825a2ad1b19e4cf2e70bfbfb3124af275c7727a82b114ba7b29a859060200160405180910390a35061079a60018055565b5050565b5f918252602082815260408084206001600160a01b0393909316845291905290205460ff1690565b5f8281526008602052604081205460ff166107e257505f610359565b5f8381526006602090815260408083206001600160a01b038616845290915281205490819003610815575f915050610359565b5f61081f8561035f565b9050805f03610832575f92505050610359565b5f858152600560205260409020548161084b828561133d565b6108559190611354565b9695505050505050565b5f8281526020819052604090206001015461087981610c97565b6104798383610d33565b61088b610d9c565b5f8281526008602052604090205460ff16156108de5760405162461bcd60e51b8152602060048201526012602482015271141c9bda9958dd081a5cc81cdd1bdc1c195960721b6044820152606401610503565b600254604051639986081d60e01b8152600481018490525f916001600160a01b031690639986081d906024015f60405180830381865afa158015610924573d5f803e3d5ffd5b505050506040513d5f823e601f3d908101601f1916820160405261094b9190810190611084565b9050805182106109945760405162461bcd60e51b8152602060048201526014602482015273125b9d985b1a59081b5a5b195cdd1bdb9948125160621b6044820152606401610503565b5f8183815181106109a7576109a76111cb565b60200260200101519050600260038111156109c4576109c4611312565b8160a0015160038111156109da576109da611312565b14610a275760405162461bcd60e51b815260206004820152601a60248201527f4d696c6573746f6e65206d75737420626520617070726f7665640000000000006044820152606401610503565b5f84815260076020908152604080832086845290915290205460ff1615610aa35760405162461bcd60e51b815260206004820152602a60248201527f46756e647320616c72656164792064697362757273656420666f722074686973604482015269206d696c6573746f6e6560b01b6064820152608401610503565b6003546040516318feeb1560e31b8152600481018690525f916001600160a01b03169063c7f758a8906024015f60405180830381865afa158015610ae9573d5f803e3d5ffd5b505050506040513d5f823e601f3d908101601f19168201604052610b109190810190611224565b905060018160a001516002811115610b2a57610b2a611312565b14610b735760405162461bcd60e51b8152602060048201526019602482015278141c9bdc1bdcd85b081b5d5cdd08189948185c1c1c9bdd9959603a1b6044820152606401610503565b60608201515f868152600560205260409020541015610bd45760405162461bcd60e51b815260206004820181905260248201527f496e73756666696369656e742066756e647320666f72206d696c6573746f6e656044820152606401610503565b5f8581526007602090815260408083208784528252808320805460ff19166001179055606085015188845260059092528220805491929091610c17908490611373565b909155505060808101516060830151600454610c3e926001600160a01b0390911691610e2d565b80608001516001600160a01b031684867f4c19cf2dc3f1cc683e5b028b77c9bf9ca97761e95b87c050f0f1f8960970922c8560600151604051610c8391815260200190565b60405180910390a450505061079a60018055565b610ca18133610e5e565b50565b5f610caf838361079e565b610d2c575f838152602081815260408083206001600160a01b03861684529091529020805460ff19166001179055610ce43390565b6001600160a01b0316826001600160a01b0316847f2f8788117e7eff1d82e926ec794901d17c78024a50270940304540a733656f0d60405160405180910390a4506001610359565b505f610359565b5f610d3e838361079e565b15610d2c575f838152602081815260408083206001600160a01b0386168085529252808320805460ff1916905551339286917ff6391f5c32d9c69d2a47ea670b442974b53935d1edc7fd64eb21e047a839171b9190a4506001610359565b600260015403610dbf57604051633ee5aeb560e01b815260040160405180910390fd5b6002600155565b6040516001600160a01b0384811660248301528381166044830152606482018390526104799186918216906323b872dd906084015b604051602081830303815290604052915060e01b6020820180516001600160e01b038381831617835250505050610e97565b6040516001600160a01b038381166024830152604482018390526104b291859182169063a9059cbb90606401610dfb565b610e68828261079e565b61079a5760405163e2517d3f60e01b81526001600160a01b038216600482015260248101839052604401610503565b5f8060205f8451602086015f885af180610eb6576040513d5f823e3d81fd5b50505f513d91508115610ecd578060011415610eda565b6001600160a01b0384163b155b1561047957604051635274afe760e01b81526001600160a01b0385166004820152602401610503565b5f60208284031215610f13575f80fd5b81356001600160e01b031981168114610f2a575f80fd5b9392505050565b5f60208284031215610f41575f80fd5b5035919050565b6001600160a01b0381168114610ca1575f80fd5b5f8060408385031215610f6d575f80fd5b823591506020830135610f7f81610f48565b809150509250929050565b5f8060408385031215610f9b575f80fd5b50508035926020909101359150565b634e487b7160e01b5f52604160045260245ffd5b60405160c0810167ffffffffffffffff81118282101715610fe157610fe1610faa565b60405290565b604051601f8201601f1916810167ffffffffffffffff8111828210171561101057611010610faa565b604052919050565b5f82601f830112611027575f80fd5b815167ffffffffffffffff81111561104157611041610faa565b611054601f8201601f1916602001610fe7565b818152846020838601011115611068575f80fd5b8160208501602083015e5f918101602001919091529392505050565b5f60208284031215611094575f80fd5b815167ffffffffffffffff8111156110aa575f80fd5b8201601f810184136110ba575f80fd5b805167ffffffffffffffff8111156110d4576110d4610faa565b8060051b6110e460208201610fe7565b918252602081840181019290810190878411156110ff575f80fd5b6020850192505b838310156111c057825167ffffffffffffffff811115611124575f80fd5b850160c0818a03601f19011215611139575f80fd5b611141610fbe565b6020828101518252604083015190820152606082015167ffffffffffffffff81111561116b575f80fd5b61117a8b602083860101611018565b604083015250608082810151606083015260a08301519082015260c09091015190600482106111a7575f80fd5b60a0810191909152825260209283019290910190611106565b979650505050505050565b634e487b7160e01b5f52603260045260245ffd5b634e487b7160e01b5f52601160045260245ffd5b80820180821115610359576103596111df565b805161121181610f48565b919050565b805160038110611211575f80fd5b5f60208284031215611234575f80fd5b815167ffffffffffffffff81111561124a575f80fd5b820160c0818503121561125b575f80fd5b611263610fbe565b81518152602082015167ffffffffffffffff811115611280575f80fd5b61128c86828501611018565b602083015250604082015167ffffffffffffffff8111156112ab575f80fd5b6112b786828501611018565b604083015250606082015167ffffffffffffffff8111156112d6575f80fd5b6112e286828501611018565b6060830152506112f460808301611206565b608082015261130560a08301611216565b60a0820152949350505050565b634e487b7160e01b5f52602160045260245ffd5b5f60208284031215611336575f80fd5b5051919050565b8082028115828204841417610359576103596111df565b5f8261136e57634e487b7160e01b5f52601260045260245ffd5b500490565b81810381811115610359576103596111df56fea26469706673582212206a02617405b2510f0fc3b6d2dcad6bbe0a8696709ed71b5405206118723bbe8764736f6c634300081a0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_COMMITTEE_ROLE = "COMMITTEE_ROLE";

    public static final String FUNC_DEFAULT_ADMIN_ROLE = "DEFAULT_ADMIN_ROLE";

    public static final String FUNC_DEPOSITFUNDS = "depositFunds";

    public static final String FUNC_DISBURSEMILESTONEFUNDS = "disburseMilestoneFunds";

    public static final String FUNC_DONATIONS = "donations";

    public static final String FUNC_GETAVAILABLEREFUND = "getAvailableRefund";

    public static final String FUNC_GETROLEADMIN = "getRoleAdmin";

    public static final String FUNC_GETTOTALFUNDSRECEIVED = "getTotalFundsReceived";

    public static final String FUNC_GRANTROLE = "grantRole";

    public static final String FUNC_HASROLE = "hasRole";

    public static final String FUNC_MILESTONEDISBURSED = "milestoneDisbursed";

    public static final String FUNC_MILESTONEMANAGER = "milestoneManager";

    public static final String FUNC_PROJECTSTOPPED = "projectStopped";

    public static final String FUNC_PROPOSALFUNDS = "proposalFunds";

    public static final String FUNC_PROPOSALMANAGER = "proposalManager";

    public static final String FUNC_RENOUNCEROLE = "renounceRole";

    public static final String FUNC_REVOKEROLE = "revokeRole";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final String FUNC_USDC = "usdc";

    public static final Event FUNDSDEPOSITED_EVENT = new Event("FundsDeposited", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event FUNDSDISBURSED_EVENT = new Event("FundsDisbursed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event ROLEADMINCHANGED_EVENT = new Event("RoleAdminChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Bytes32>(true) {}, new TypeReference<Bytes32>(true) {}));
    ;

    public static final Event ROLEGRANTED_EVENT = new Event("RoleGranted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event ROLEREVOKED_EVENT = new Event("RoleRevoked", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    @Deprecated
    protected FundManager(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected FundManager(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected FundManager(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected FundManager(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<FundsDepositedEventResponse> getFundsDepositedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(FUNDSDEPOSITED_EVENT, transactionReceipt);
        ArrayList<FundsDepositedEventResponse> responses = new ArrayList<FundsDepositedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            FundsDepositedEventResponse typedResponse = new FundsDepositedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.donor = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static FundsDepositedEventResponse getFundsDepositedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(FUNDSDEPOSITED_EVENT, log);
        FundsDepositedEventResponse typedResponse = new FundsDepositedEventResponse();
        typedResponse.log = log;
        typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.donor = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<FundsDepositedEventResponse> fundsDepositedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getFundsDepositedEventFromLog(log));
    }

    public Flowable<FundsDepositedEventResponse> fundsDepositedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(FUNDSDEPOSITED_EVENT));
        return fundsDepositedEventFlowable(filter);
    }

    public static List<FundsDisbursedEventResponse> getFundsDisbursedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(FUNDSDISBURSED_EVENT, transactionReceipt);
        ArrayList<FundsDisbursedEventResponse> responses = new ArrayList<FundsDisbursedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            FundsDisbursedEventResponse typedResponse = new FundsDisbursedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.milestoneId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.student = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static FundsDisbursedEventResponse getFundsDisbursedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(FUNDSDISBURSED_EVENT, log);
        FundsDisbursedEventResponse typedResponse = new FundsDisbursedEventResponse();
        typedResponse.log = log;
        typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.milestoneId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.student = (String) eventValues.getIndexedValues().get(2).getValue();
        typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<FundsDisbursedEventResponse> fundsDisbursedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getFundsDisbursedEventFromLog(log));
    }

    public Flowable<FundsDisbursedEventResponse> fundsDisbursedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(FUNDSDISBURSED_EVENT));
        return fundsDisbursedEventFlowable(filter);
    }

    public static List<RoleAdminChangedEventResponse> getRoleAdminChangedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ROLEADMINCHANGED_EVENT, transactionReceipt);
        ArrayList<RoleAdminChangedEventResponse> responses = new ArrayList<RoleAdminChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RoleAdminChangedEventResponse typedResponse = new RoleAdminChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.previousAdminRole = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.newAdminRole = (byte[]) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static RoleAdminChangedEventResponse getRoleAdminChangedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ROLEADMINCHANGED_EVENT, log);
        RoleAdminChangedEventResponse typedResponse = new RoleAdminChangedEventResponse();
        typedResponse.log = log;
        typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.previousAdminRole = (byte[]) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.newAdminRole = (byte[]) eventValues.getIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<RoleAdminChangedEventResponse> roleAdminChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getRoleAdminChangedEventFromLog(log));
    }

    public Flowable<RoleAdminChangedEventResponse> roleAdminChangedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ROLEADMINCHANGED_EVENT));
        return roleAdminChangedEventFlowable(filter);
    }

    public static List<RoleGrantedEventResponse> getRoleGrantedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ROLEGRANTED_EVENT, transactionReceipt);
        ArrayList<RoleGrantedEventResponse> responses = new ArrayList<RoleGrantedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RoleGrantedEventResponse typedResponse = new RoleGrantedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.sender = (String) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static RoleGrantedEventResponse getRoleGrantedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ROLEGRANTED_EVENT, log);
        RoleGrantedEventResponse typedResponse = new RoleGrantedEventResponse();
        typedResponse.log = log;
        typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.sender = (String) eventValues.getIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<RoleGrantedEventResponse> roleGrantedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getRoleGrantedEventFromLog(log));
    }

    public Flowable<RoleGrantedEventResponse> roleGrantedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ROLEGRANTED_EVENT));
        return roleGrantedEventFlowable(filter);
    }

    public static List<RoleRevokedEventResponse> getRoleRevokedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ROLEREVOKED_EVENT, transactionReceipt);
        ArrayList<RoleRevokedEventResponse> responses = new ArrayList<RoleRevokedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RoleRevokedEventResponse typedResponse = new RoleRevokedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.sender = (String) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static RoleRevokedEventResponse getRoleRevokedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ROLEREVOKED_EVENT, log);
        RoleRevokedEventResponse typedResponse = new RoleRevokedEventResponse();
        typedResponse.log = log;
        typedResponse.role = (byte[]) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.account = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.sender = (String) eventValues.getIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<RoleRevokedEventResponse> roleRevokedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getRoleRevokedEventFromLog(log));
    }

    public Flowable<RoleRevokedEventResponse> roleRevokedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ROLEREVOKED_EVENT));
        return roleRevokedEventFlowable(filter);
    }

    public RemoteFunctionCall<byte[]> COMMITTEE_ROLE() {
        final Function function = new Function(FUNC_COMMITTEE_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<byte[]> DEFAULT_ADMIN_ROLE() {
        final Function function = new Function(FUNC_DEFAULT_ADMIN_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<TransactionReceipt> depositFunds(BigInteger _proposalId,
            BigInteger _amount) {
        final Function function = new Function(
                FUNC_DEPOSITFUNDS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_proposalId), 
                new org.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> disburseMilestoneFunds(BigInteger _proposalId,
            BigInteger _milestoneId) {
        final Function function = new Function(
                FUNC_DISBURSEMILESTONEFUNDS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_proposalId), 
                new org.web3j.abi.datatypes.generated.Uint256(_milestoneId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> donations(BigInteger param0, String param1) {
        final Function function = new Function(FUNC_DONATIONS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0), 
                new org.web3j.abi.datatypes.Address(160, param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getAvailableRefund(BigInteger _proposalId,
            String _donor) {
        final Function function = new Function(FUNC_GETAVAILABLEREFUND, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_proposalId), 
                new org.web3j.abi.datatypes.Address(160, _donor)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<byte[]> getRoleAdmin(byte[] role) {
        final Function function = new Function(FUNC_GETROLEADMIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<BigInteger> getTotalFundsReceived(BigInteger _proposalId) {
        final Function function = new Function(FUNC_GETTOTALFUNDSRECEIVED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_proposalId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> grantRole(byte[] role, String account) {
        final Function function = new Function(
                FUNC_GRANTROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.web3j.abi.datatypes.Address(160, account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> hasRole(byte[] role, String account) {
        final Function function = new Function(FUNC_HASROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.web3j.abi.datatypes.Address(160, account)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> milestoneDisbursed(BigInteger param0, BigInteger param1) {
        final Function function = new Function(FUNC_MILESTONEDISBURSED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> milestoneManager() {
        final Function function = new Function(FUNC_MILESTONEMANAGER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Boolean> projectStopped(BigInteger param0) {
        final Function function = new Function(FUNC_PROJECTSTOPPED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> proposalFunds(BigInteger param0) {
        final Function function = new Function(FUNC_PROPOSALFUNDS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> proposalManager() {
        final Function function = new Function(FUNC_PROPOSALMANAGER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceRole(byte[] role,
            String callerConfirmation) {
        final Function function = new Function(
                FUNC_RENOUNCEROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.web3j.abi.datatypes.Address(160, callerConfirmation)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> revokeRole(byte[] role, String account) {
        final Function function = new Function(
                FUNC_REVOKEROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.web3j.abi.datatypes.Address(160, account)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceId) {
        final Function function = new Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> usdc() {
        final Function function = new Function(FUNC_USDC, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    @Deprecated
    public static FundManager load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new FundManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static FundManager load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new FundManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static FundManager load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new FundManager(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static FundManager load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new FundManager(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<FundManager> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider, String _milestoneManager,
            String _proposalManager, String _usdc) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _milestoneManager), 
                new org.web3j.abi.datatypes.Address(160, _proposalManager), 
                new org.web3j.abi.datatypes.Address(160, _usdc)));
        return deployRemoteCall(FundManager.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<FundManager> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider, String _milestoneManager,
            String _proposalManager, String _usdc) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _milestoneManager), 
                new org.web3j.abi.datatypes.Address(160, _proposalManager), 
                new org.web3j.abi.datatypes.Address(160, _usdc)));
        return deployRemoteCall(FundManager.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<FundManager> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit, String _milestoneManager,
            String _proposalManager, String _usdc) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _milestoneManager), 
                new org.web3j.abi.datatypes.Address(160, _proposalManager), 
                new org.web3j.abi.datatypes.Address(160, _usdc)));
        return deployRemoteCall(FundManager.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<FundManager> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit, String _milestoneManager,
            String _proposalManager, String _usdc) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _milestoneManager), 
                new org.web3j.abi.datatypes.Address(160, _proposalManager), 
                new org.web3j.abi.datatypes.Address(160, _usdc)));
        return deployRemoteCall(FundManager.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    public static void linkLibraries(List<Contract.LinkReference> references) {
        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class FundsDepositedEventResponse extends BaseEventResponse {
        public BigInteger proposalId;

        public String donor;

        public BigInteger amount;
    }

    public static class FundsDisbursedEventResponse extends BaseEventResponse {
        public BigInteger proposalId;

        public BigInteger milestoneId;

        public String student;

        public BigInteger amount;
    }

    public static class RoleAdminChangedEventResponse extends BaseEventResponse {
        public byte[] role;

        public byte[] previousAdminRole;

        public byte[] newAdminRole;
    }

    public static class RoleGrantedEventResponse extends BaseEventResponse {
        public byte[] role;

        public String account;

        public String sender;
    }

    public static class RoleRevokedEventResponse extends BaseEventResponse {
        public byte[] role;

        public String account;

        public String sender;
    }
}
