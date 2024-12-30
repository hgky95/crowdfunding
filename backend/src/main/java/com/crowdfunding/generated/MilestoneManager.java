package com.crowdfunding.generated;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/hyperledger-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.12.3.
 */
@SuppressWarnings("rawtypes")
public class MilestoneManager extends Contract {
    public static final String BINARY = "608060405234801561000f575f80fd5b5060405161160f38038061160f83398101604081905261002e916100ea565b806100395f82610041565b505050610117565b5f828152602081815260408083206001600160a01b038516845290915281205460ff166100e1575f838152602081815260408083206001600160a01b03861684529091529020805460ff191660011790556100993390565b6001600160a01b0316826001600160a01b0316847f2f8788117e7eff1d82e926ec794901d17c78024a50270940304540a733656f0d60405160405180910390a45060016100e4565b505f5b92915050565b5f602082840312156100fa575f80fd5b81516001600160a01b0381168114610110575f80fd5b9392505050565b6114eb806101245f395ff3fe608060405234801561000f575f80fd5b5060043610610132575f3560e01c80637053e204116100b4578063bd06ea1911610079578063bd06ea19146102aa578063d547741f146102bd578063de8ec63f146102d0578063f1e70617146102e3578063f7be43fb14610303578063f9643a0c14610317575f80fd5b80637053e2041461023757806391d148541461025c5780639986081d1461026f578063a217fddf1461028f578063ad32563d14610296575f80fd5b806336568abe116100fa57806336568abe146101c957806338046217146101dc57806356c11367146101ef57806357a39da6146102025780636e2a373a14610224575f80fd5b806301ffc9a7146101365780631fddd0441461015e57806320913da514610173578063248a9ca3146101865780632f2ff15d146101b6575b5f80fd5b610149610144366004610f27565b61032a565b60405190151581526020015b60405180910390f35b61017161016c366004610ff4565b610360565b005b610171610181366004611040565b610531565b6101a8610194366004611060565b5f9081526020819052604090206001015490565b604051908152602001610155565b6101716101c4366004611092565b610623565b6101716101d7366004611092565b61064d565b6101716101ea3660046110bc565b610685565b6101716101fd3660046110bc565b6106a6565b610215610210366004611060565b6106c7565b60405161015593929190611103565b610171610232366004610ff4565b61076f565b61024a610245366004611040565b610872565b6040516101559695949392919061115f565b61014961026a366004611092565b61094f565b61028261027d366004611060565b610977565b60405161015591906111a3565b6101a85f81565b6101a85f8051602061149683398151915281565b6101716102b83660046110bc565b610ace565b6101716102cb366004611092565b610aef565b6101716102de3660046110bc565b610b13565b6102f66102f1366004611040565b610b34565b604051610155919061124f565b6101a85f8051602061147683398151915281565b61017161032536600461128a565b610c46565b5f6001600160e01b03198216637965db0b60e01b148061035a57506301ffc9a760e01b6001600160e01b03198316145b92915050565b5f8051602061147683398151915261037781610de5565b5f848152600160205260408120805485908110610396576103966112f0565b5f918252602090912060069091020190506002600582015460ff1660038111156103c2576103c261112b565b036104145760405162461bcd60e51b815260206004820152601b60248201527f4d696c6573746f6e6520616c7265616479207375626d6974746564000000000060448201526064015b60405180910390fd5b80600401544211156104685760405162461bcd60e51b815260206004820152601d60248201527f4d696c6573746f6e6520646561646c696e652068617320706173736564000000604482015260640161040b565b604080516020808201889052818301879052825180830384018152606090920190925280519101205f9060408051606081018252878152602080820188815242838501525f85815260029092529290208151815591519293509160018201906104d19082611387565b5060409182015160029091015560058301805460ff19166001179055513390869088907f514888eb1a7c1fae361e760cbec737733349d30668d4a8c42659a829a534c76a90610521908990611442565b60405180910390a4505050505050565b5f8051602061149683398151915261054881610de5565b5f838152600160205260408120805484908110610567576105676112f0565b5f918252602090912060069091020190506002600582015460ff1660038111156105935761059361112b565b036105e05760405162461bcd60e51b815260206004820152601a60248201527f4d696c6573746f6e6520616c726561647920617070726f766564000000000000604482015260640161040b565b60058101805460ff191660021790556040513390849086907f9ffbb52f2a1362273cd74689a516ca4f52c4282dc13e2e68b7e0646a9a7af7a3905f90a450505050565b5f8281526020819052604090206001015461063d81610de5565b6106478383610df2565b50505050565b6001600160a01b03811633146106765760405163334bd91960e11b815260040160405180910390fd5b6106808282610e81565b505050565b5f61068f81610de5565b6106805f8051602061149683398151915283610df2565b5f6106b081610de5565b6106805f8051602061149683398151915283610e81565b60026020525f9081526040902080546001820180549192916106e890611304565b80601f016020809104026020016040519081016040528092919081815260200182805461071490611304565b801561075f5780601f106107365761010080835404028352916020019161075f565b820191905f5260205f20905b81548152906001019060200180831161074257829003601f168201915b5050505050908060020154905083565b5f8051602061149683398151915261078681610de5565b5f8481526001602052604081208054859081106107a5576107a56112f0565b5f918252602090912060069091020190506002600582015460ff1660038111156107d1576107d161112b565b0361081e5760405162461bcd60e51b815260206004820152601a60248201527f4d696c6573746f6e6520616c726561647920617070726f766564000000000000604482015260640161040b565b60058101805460ff191660031790556040513390859087907f4dfc84da87020df96522cd1be73d8e0a2d933702208e9a44d3f7358ed88a9cd390610863908890611442565b60405180910390a45050505050565b6001602052815f5260405f20818154811061088b575f80fd5b905f5260205f2090600602015f9150915050805f0154908060010154908060020180546108b790611304565b80601f01602080910402602001604051908101604052809291908181526020018280546108e390611304565b801561092e5780601f106109055761010080835404028352916020019161092e565b820191905f5260205f20905b81548152906001019060200180831161091157829003601f168201915b50505050600383015460048401546005909401549293909290915060ff1686565b5f918252602082815260408084206001600160a01b0393909316845291905290205460ff1690565b606060015f8381526020019081526020015f20805480602002602001604051908101604052809291908181526020015f905b82821015610ac3578382905f5260205f2090600602016040518060c00160405290815f8201548152602001600182015481526020016002820180546109ed90611304565b80601f0160208091040260200160405190810160405280929190818152602001828054610a1990611304565b8015610a645780601f10610a3b57610100808354040283529160200191610a64565b820191905f5260205f20905b815481529060010190602001808311610a4757829003601f168201915b5050509183525050600382810154602083015260048301546040830152600583015460609092019160ff1690811115610a9f57610a9f61112b565b6003811115610ab057610ab061112b565b81525050815260200190600101906109a9565b505050509050919050565b5f610ad881610de5565b6106805f8051602061147683398151915283610df2565b5f82815260208190526040902060010154610b0981610de5565b6106478383610e81565b5f610b1d81610de5565b6106805f8051602061147683398151915283610e81565b610b5660405180606001604052805f8152602001606081526020015f81525090565b6040805160208082018690528183018590528251808303840181526060909201909252805191012060025f8281526020019081526020015f206040518060600160405290815f8201548152602001600182018054610bb390611304565b80601f0160208091040260200160405190810160405280929190818152602001828054610bdf90611304565b8015610c2a5780601f10610c0157610100808354040283529160200191610c2a565b820191905f5260205f20905b815481529060010190602001808311610c0d57829003601f168201915b5050505050815260200160028201548152505091505092915050565b610c5d5f805160206114968339815191528261094f565b610ca95760405162461bcd60e51b815260206004820152601f60248201527f43616c6c6572206d757374206861766520636f6d6d697474656520726f6c6500604482015260640161040b565b5f8581526001602052604081205415610ccf575f86815260016020526040902054610cd1565b5f5b90505f6040518060c001604052808381526020018881526020018781526020018681526020018581526020015f6003811115610d0f57610d0f61112b565b90525f88815260016020818152604080842080548085018255908552938290208551600690950201938455908401519183019190915582015191925082916002820190610d5c9082611387565b50606082015181600301556080820151816004015560a0820151816005015f6101000a81548160ff02191690836003811115610d9a57610d9a61112b565b0217905550505081877fdb69cd106eb6f7f884a1210c362d72e26df42e3141159144bd689bd5e0dd582a8888604051610dd4929190611454565b60405180910390a350505050505050565b610def8133610eea565b50565b5f610dfd838361094f565b610e7a575f838152602081815260408083206001600160a01b03861684529091529020805460ff19166001179055610e323390565b6001600160a01b0316826001600160a01b0316847f2f8788117e7eff1d82e926ec794901d17c78024a50270940304540a733656f0d60405160405180910390a450600161035a565b505f61035a565b5f610e8c838361094f565b15610e7a575f838152602081815260408083206001600160a01b0386168085529252808320805460ff1916905551339286917ff6391f5c32d9c69d2a47ea670b442974b53935d1edc7fd64eb21e047a839171b9190a450600161035a565b610ef4828261094f565b610f235760405163e2517d3f60e01b81526001600160a01b03821660048201526024810183905260440161040b565b5050565b5f60208284031215610f37575f80fd5b81356001600160e01b031981168114610f4e575f80fd5b9392505050565b634e487b7160e01b5f52604160045260245ffd5b5f82601f830112610f78575f80fd5b813567ffffffffffffffff811115610f9257610f92610f55565b604051601f8201601f19908116603f0116810167ffffffffffffffff81118282101715610fc157610fc1610f55565b604052818152838201602001851015610fd8575f80fd5b816020850160208301375f918101602001919091529392505050565b5f805f60608486031215611006575f80fd5b8335925060208401359150604084013567ffffffffffffffff81111561102a575f80fd5b61103686828701610f69565b9150509250925092565b5f8060408385031215611051575f80fd5b50508035926020909101359150565b5f60208284031215611070575f80fd5b5035919050565b80356001600160a01b038116811461108d575f80fd5b919050565b5f80604083850312156110a3575f80fd5b823591506110b360208401611077565b90509250929050565b5f602082840312156110cc575f80fd5b610f4e82611077565b5f81518084528060208401602086015e5f602082860101526020601f19601f83011685010191505092915050565b838152606060208201525f61111b60608301856110d5565b9050826040830152949350505050565b634e487b7160e01b5f52602160045260245ffd5b6004811061115b57634e487b7160e01b5f52602160045260245ffd5b9052565b86815285602082015260c060408201525f61117d60c08301876110d5565b905084606083015283608083015261119860a083018461113f565b979650505050505050565b5f602082016020835280845180835260408501915060408160051b8601019250602086015f5b8281101561124357603f1987860301845281518051865260208101516020870152604081015160c0604088015261120360c08801826110d5565b9050606082015160608801526080820151608088015260a0820151915061122d60a088018361113f565b95505060209384019391909101906001016111c9565b50929695505050505050565b60208152815160208201525f60208301516060604084015261127460808401826110d5565b9050604084015160608401528091505092915050565b5f805f805f60a0868803121561129e575f80fd5b85359450602086013567ffffffffffffffff8111156112bb575f80fd5b6112c788828901610f69565b94505060408601359250606086013591506112e460808701611077565b90509295509295909350565b634e487b7160e01b5f52603260045260245ffd5b600181811c9082168061131857607f821691505b60208210810361133657634e487b7160e01b5f52602260045260245ffd5b50919050565b601f82111561068057805f5260205f20601f840160051c810160208510156113615750805b601f840160051c820191505b81811015611380575f815560010161136d565b5050505050565b815167ffffffffffffffff8111156113a1576113a1610f55565b6113b5816113af8454611304565b8461133c565b6020601f8211600181146113e7575f83156113d05750848201515b5f19600385901b1c1916600184901b178455611380565b5f84815260208120601f198516915b8281101561141657878501518255602094850194600190920191016113f6565b508482101561143357868401515f19600387901b60f8161c191681555b50505050600190811b01905550565b602081525f610f4e60208301846110d5565b604081525f61146660408301856110d5565b9050826020830152939250505056fe36a5c4aaacb6b388bbd448bf11096b7dafc5652bcc9046084fd0e95b1fb0b2cc794daa56950487582951e8db2fdbcbee68c2223c65641d0aa02a3afc64f9a86fa2646970667358221220d7cecd3fae42d8fdcea2032ef3f4ddc5952adc61c44d8d5dcb6d95f75d06069a64736f6c634300081a0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_COMMITTEE_ROLE = "COMMITTEE_ROLE";

    public static final String FUNC_DEFAULT_ADMIN_ROLE = "DEFAULT_ADMIN_ROLE";

    public static final String FUNC_STUDENT_ROLE = "STUDENT_ROLE";

    public static final String FUNC_ADDCOMMITTEE = "addCommittee";

    public static final String FUNC_ADDSTUDENT = "addStudent";

    public static final String FUNC_APPROVEMILESTONE = "approveMilestone";

    public static final String FUNC_CREATEMILESTONEPLAN = "createMilestonePlan";

    public static final String FUNC_GETMILESTONEPLANSBYPROPOSAL = "getMilestonePlansByProposal";

    public static final String FUNC_GETMILESTONESUBMISSION = "getMilestoneSubmission";

    public static final String FUNC_GETROLEADMIN = "getRoleAdmin";

    public static final String FUNC_GRANTROLE = "grantRole";

    public static final String FUNC_HASROLE = "hasRole";

    public static final String FUNC_MILESTONEPLANS = "milestonePlans";

    public static final String FUNC_MILESTONESUBMISSIONS = "milestoneSubmissions";

    public static final String FUNC_REJECTMILESTONE = "rejectMilestone";

    public static final String FUNC_RENOUNCEROLE = "renounceRole";

    public static final String FUNC_REVOKECOMMITTEE = "revokeCommittee";

    public static final String FUNC_REVOKEROLE = "revokeRole";

    public static final String FUNC_REVOKESTUDENT = "revokeStudent";

    public static final String FUNC_SUBMITMILESTONERESULT = "submitMilestoneResult";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final Event MILESTONEAPPROVED_EVENT = new Event("MilestoneApproved", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event MILESTONEPLANCREATED_EVENT = new Event("MilestonePlanCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event MILESTONEPLANRESULTSUBMITTED_EVENT = new Event("MilestonePlanResultSubmitted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Utf8String>() {}));
    ;

    public static final Event MILESTONEREJECTED_EVENT = new Event("MilestoneRejected", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Utf8String>() {}));
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
    protected MilestoneManager(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected MilestoneManager(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected MilestoneManager(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected MilestoneManager(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<MilestoneApprovedEventResponse> getMilestoneApprovedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(MILESTONEAPPROVED_EVENT, transactionReceipt);
        ArrayList<MilestoneApprovedEventResponse> responses = new ArrayList<MilestoneApprovedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MilestoneApprovedEventResponse typedResponse = new MilestoneApprovedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.milestoneId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.updaterAddr = (String) eventValues.getIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static MilestoneApprovedEventResponse getMilestoneApprovedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(MILESTONEAPPROVED_EVENT, log);
        MilestoneApprovedEventResponse typedResponse = new MilestoneApprovedEventResponse();
        typedResponse.log = log;
        typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.milestoneId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.updaterAddr = (String) eventValues.getIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<MilestoneApprovedEventResponse> milestoneApprovedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getMilestoneApprovedEventFromLog(log));
    }

    public Flowable<MilestoneApprovedEventResponse> milestoneApprovedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MILESTONEAPPROVED_EVENT));
        return milestoneApprovedEventFlowable(filter);
    }

    public static List<MilestonePlanCreatedEventResponse> getMilestonePlanCreatedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(MILESTONEPLANCREATED_EVENT, transactionReceipt);
        ArrayList<MilestonePlanCreatedEventResponse> responses = new ArrayList<MilestonePlanCreatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MilestonePlanCreatedEventResponse typedResponse = new MilestonePlanCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.milestoneId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.description = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.fundingAmount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static MilestonePlanCreatedEventResponse getMilestonePlanCreatedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(MILESTONEPLANCREATED_EVENT, log);
        MilestonePlanCreatedEventResponse typedResponse = new MilestonePlanCreatedEventResponse();
        typedResponse.log = log;
        typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.milestoneId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.description = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.fundingAmount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<MilestonePlanCreatedEventResponse> milestonePlanCreatedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getMilestonePlanCreatedEventFromLog(log));
    }

    public Flowable<MilestonePlanCreatedEventResponse> milestonePlanCreatedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MILESTONEPLANCREATED_EVENT));
        return milestonePlanCreatedEventFlowable(filter);
    }

    public static List<MilestonePlanResultSubmittedEventResponse> getMilestonePlanResultSubmittedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(MILESTONEPLANRESULTSUBMITTED_EVENT, transactionReceipt);
        ArrayList<MilestonePlanResultSubmittedEventResponse> responses = new ArrayList<MilestonePlanResultSubmittedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MilestonePlanResultSubmittedEventResponse typedResponse = new MilestonePlanResultSubmittedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.milestoneId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.student = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.content = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static MilestonePlanResultSubmittedEventResponse getMilestonePlanResultSubmittedEventFromLog(
            Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(MILESTONEPLANRESULTSUBMITTED_EVENT, log);
        MilestonePlanResultSubmittedEventResponse typedResponse = new MilestonePlanResultSubmittedEventResponse();
        typedResponse.log = log;
        typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.milestoneId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.student = (String) eventValues.getIndexedValues().get(2).getValue();
        typedResponse.content = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<MilestonePlanResultSubmittedEventResponse> milestonePlanResultSubmittedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getMilestonePlanResultSubmittedEventFromLog(log));
    }

    public Flowable<MilestonePlanResultSubmittedEventResponse> milestonePlanResultSubmittedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MILESTONEPLANRESULTSUBMITTED_EVENT));
        return milestonePlanResultSubmittedEventFlowable(filter);
    }

    public static List<MilestoneRejectedEventResponse> getMilestoneRejectedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(MILESTONEREJECTED_EVENT, transactionReceipt);
        ArrayList<MilestoneRejectedEventResponse> responses = new ArrayList<MilestoneRejectedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MilestoneRejectedEventResponse typedResponse = new MilestoneRejectedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.milestoneId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.updaterAddr = (String) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.reason = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static MilestoneRejectedEventResponse getMilestoneRejectedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(MILESTONEREJECTED_EVENT, log);
        MilestoneRejectedEventResponse typedResponse = new MilestoneRejectedEventResponse();
        typedResponse.log = log;
        typedResponse.proposalId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.milestoneId = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.updaterAddr = (String) eventValues.getIndexedValues().get(2).getValue();
        typedResponse.reason = (String) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<MilestoneRejectedEventResponse> milestoneRejectedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getMilestoneRejectedEventFromLog(log));
    }

    public Flowable<MilestoneRejectedEventResponse> milestoneRejectedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MILESTONEREJECTED_EVENT));
        return milestoneRejectedEventFlowable(filter);
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

    public RemoteFunctionCall<byte[]> STUDENT_ROLE() {
        final Function function = new Function(FUNC_STUDENT_ROLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<TransactionReceipt> addCommittee(String member) {
        final Function function = new Function(
                FUNC_ADDCOMMITTEE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, member)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addStudent(String student) {
        final Function function = new Function(
                FUNC_ADDSTUDENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, student)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> approveMilestone(BigInteger _proposalId,
            BigInteger _milestoneId) {
        final Function function = new Function(
                FUNC_APPROVEMILESTONE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_proposalId), 
                new org.web3j.abi.datatypes.generated.Uint256(_milestoneId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> createMilestonePlan(BigInteger _proposalId,
            String _description, BigInteger _fundingAmount, BigInteger _deadline, String caller) {
        final Function function = new Function(
                FUNC_CREATEMILESTONEPLAN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_proposalId), 
                new org.web3j.abi.datatypes.Utf8String(_description), 
                new org.web3j.abi.datatypes.generated.Uint256(_fundingAmount), 
                new org.web3j.abi.datatypes.generated.Uint256(_deadline), 
                new org.web3j.abi.datatypes.Address(160, caller)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getMilestonePlansByProposal(BigInteger _proposalId) {
        final Function function = new Function(FUNC_GETMILESTONEPLANSBYPROPOSAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_proposalId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<MilestonePlan>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<MilestoneSubmission> getMilestoneSubmission(BigInteger _proposalId,
            BigInteger _milestoneId) {
        final Function function = new Function(FUNC_GETMILESTONESUBMISSION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_proposalId), 
                new org.web3j.abi.datatypes.generated.Uint256(_milestoneId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<MilestoneSubmission>() {}));
        return executeRemoteCallSingleValueReturn(function, MilestoneSubmission.class);
    }

    public RemoteFunctionCall<byte[]> getRoleAdmin(byte[] role) {
        final Function function = new Function(FUNC_GETROLEADMIN, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
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

    public RemoteFunctionCall<Tuple6<BigInteger, BigInteger, String, BigInteger, BigInteger, BigInteger>> milestonePlans(
            BigInteger param0, BigInteger param1) {
        final Function function = new Function(FUNC_MILESTONEPLANS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0), 
                new org.web3j.abi.datatypes.generated.Uint256(param1)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}));
        return new RemoteFunctionCall<Tuple6<BigInteger, BigInteger, String, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple6<BigInteger, BigInteger, String, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple6<BigInteger, BigInteger, String, BigInteger, BigInteger, BigInteger> call(
                            ) throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<BigInteger, BigInteger, String, BigInteger, BigInteger, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue());
                    }
                });
    }

    public RemoteFunctionCall<Tuple3<BigInteger, String, BigInteger>> milestoneSubmissions(
            byte[] param0) {
        final Function function = new Function(FUNC_MILESTONESUBMISSIONS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<BigInteger, String, BigInteger>>(function,
                new Callable<Tuple3<BigInteger, String, BigInteger>>() {
                    @Override
                    public Tuple3<BigInteger, String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<BigInteger, String, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> rejectMilestone(BigInteger _proposalId,
            BigInteger _milestoneId, String rejectedReason) {
        final Function function = new Function(
                FUNC_REJECTMILESTONE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_proposalId), 
                new org.web3j.abi.datatypes.generated.Uint256(_milestoneId), 
                new org.web3j.abi.datatypes.Utf8String(rejectedReason)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public RemoteFunctionCall<TransactionReceipt> revokeCommittee(String member) {
        final Function function = new Function(
                FUNC_REVOKECOMMITTEE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, member)), 
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

    public RemoteFunctionCall<TransactionReceipt> revokeStudent(String student) {
        final Function function = new Function(
                FUNC_REVOKESTUDENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, student)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> submitMilestoneResult(BigInteger _proposalId,
            BigInteger _milestoneId, String _content) {
        final Function function = new Function(
                FUNC_SUBMITMILESTONERESULT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_proposalId), 
                new org.web3j.abi.datatypes.generated.Uint256(_milestoneId), 
                new org.web3j.abi.datatypes.Utf8String(_content)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> supportsInterface(byte[] interfaceId) {
        final Function function = new Function(FUNC_SUPPORTSINTERFACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes4(interfaceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    @Deprecated
    public static MilestoneManager load(String contractAddress, Web3j web3j,
            Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new MilestoneManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static MilestoneManager load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MilestoneManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static MilestoneManager load(String contractAddress, Web3j web3j,
            Credentials credentials, ContractGasProvider contractGasProvider) {
        return new MilestoneManager(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static MilestoneManager load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new MilestoneManager(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<MilestoneManager> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider, String initialAdmin) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, initialAdmin)));
        return deployRemoteCall(MilestoneManager.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<MilestoneManager> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider,
            String initialAdmin) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, initialAdmin)));
        return deployRemoteCall(MilestoneManager.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<MilestoneManager> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit, String initialAdmin) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, initialAdmin)));
        return deployRemoteCall(MilestoneManager.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<MilestoneManager> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit,
            String initialAdmin) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, initialAdmin)));
        return deployRemoteCall(MilestoneManager.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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

    public static class MilestonePlan extends DynamicStruct {
        public BigInteger id;

        public BigInteger proposalId;

        public String description;

        public BigInteger fundingAmount;

        public BigInteger deadline;

        public BigInteger status;

        public MilestonePlan(BigInteger id, BigInteger proposalId, String description,
                BigInteger fundingAmount, BigInteger deadline, BigInteger status) {
            super(new org.web3j.abi.datatypes.generated.Uint256(id), 
                    new org.web3j.abi.datatypes.generated.Uint256(proposalId), 
                    new org.web3j.abi.datatypes.Utf8String(description), 
                    new org.web3j.abi.datatypes.generated.Uint256(fundingAmount), 
                    new org.web3j.abi.datatypes.generated.Uint256(deadline), 
                    new org.web3j.abi.datatypes.generated.Uint8(status));
            this.id = id;
            this.proposalId = proposalId;
            this.description = description;
            this.fundingAmount = fundingAmount;
            this.deadline = deadline;
            this.status = status;
        }

        public MilestonePlan(Uint256 id, Uint256 proposalId, Utf8String description,
                Uint256 fundingAmount, Uint256 deadline, Uint8 status) {
            super(id, proposalId, description, fundingAmount, deadline, status);
            this.id = id.getValue();
            this.proposalId = proposalId.getValue();
            this.description = description.getValue();
            this.fundingAmount = fundingAmount.getValue();
            this.deadline = deadline.getValue();
            this.status = status.getValue();
        }
    }

    public static class MilestoneSubmission extends DynamicStruct {
        public BigInteger milestoneId;

        public String content;

        public BigInteger submissionDate;

        public MilestoneSubmission(BigInteger milestoneId, String content,
                BigInteger submissionDate) {
            super(new org.web3j.abi.datatypes.generated.Uint256(milestoneId), 
                    new org.web3j.abi.datatypes.Utf8String(content), 
                    new org.web3j.abi.datatypes.generated.Uint256(submissionDate));
            this.milestoneId = milestoneId;
            this.content = content;
            this.submissionDate = submissionDate;
        }

        public MilestoneSubmission(Uint256 milestoneId, Utf8String content,
                Uint256 submissionDate) {
            super(milestoneId, content, submissionDate);
            this.milestoneId = milestoneId.getValue();
            this.content = content.getValue();
            this.submissionDate = submissionDate.getValue();
        }
    }

    public static class MilestoneApprovedEventResponse extends BaseEventResponse {
        public BigInteger proposalId;

        public BigInteger milestoneId;

        public String updaterAddr;
    }

    public static class MilestonePlanCreatedEventResponse extends BaseEventResponse {
        public BigInteger proposalId;

        public BigInteger milestoneId;

        public String description;

        public BigInteger fundingAmount;
    }

    public static class MilestonePlanResultSubmittedEventResponse extends BaseEventResponse {
        public BigInteger proposalId;

        public BigInteger milestoneId;

        public String student;

        public String content;
    }

    public static class MilestoneRejectedEventResponse extends BaseEventResponse {
        public BigInteger proposalId;

        public BigInteger milestoneId;

        public String updaterAddr;

        public String reason;
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
