package com.crowdfunding.contracts;

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
 * <a href="https://github.com/hyperledger/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.12.2.
 */
@SuppressWarnings("rawtypes")
public class MilestoneManager extends Contract {
    public static final String BINARY = "608060405234801561000f575f80fd5b5060405161178238038061178283398101604081905261002e916100ea565b806100395f82610041565b505050610117565b5f828152602081815260408083206001600160a01b038516845290915281205460ff166100e1575f838152602081815260408083206001600160a01b03861684529091529020805460ff191660011790556100993390565b6001600160a01b0316826001600160a01b0316847f2f8788117e7eff1d82e926ec794901d17c78024a50270940304540a733656f0d60405160405180910390a45060016100e4565b505f5b92915050565b5f602082840312156100fa575f80fd5b81516001600160a01b0381168114610110575f80fd5b9392505050565b61165e806101245f395ff3fe608060405234801561000f575f80fd5b5060043610610153575f3560e01c806382f7d392116100bf578063bd06ea1911610079578063bd06ea1914610304578063c864be8a14610317578063d547741f1461032b578063f1e706171461033e578063f7be43fb1461035e578063f9643a0c14610372575f80fd5b806382f7d392146102905780638c9540d3146102a357806391d14854146102b65780639986081d146102c9578063a217fddf146102e9578063ad32563d146102f0575f80fd5b80632f2ff15d116101105780632f2ff15d146101fd57806336568abe14610210578063380462171461022357806357a39da6146102365780636e2a373a146102585780637053e2041461026b575f80fd5b806301ffc9a7146101575780631fddd0441461017f57806320913da514610194578063248a9ca3146101a757806325746834146101d75780632bc3d7aa146101ea575b5f80fd5b61016a61016536600461107a565b610385565b60405190151581526020015b60405180910390f35b61019261018d366004611147565b6103bb565b005b6101926101a2366004611193565b61058c565b6101c96101b53660046111b3565b5f9081526020819052604090206001015490565b604051908152602001610176565b6101926101e53660046111e5565b61067e565b61016a6101f83660046111e5565b6106e6565b61019261020b3660046111fe565b6106fe565b61019261021e3660046111fe565b610728565b6101926102313660046111e5565b610760565b6102496102443660046111b3565b6107c8565b60405161017693929190611256565b610192610266366004611147565b610870565b61027e610279366004611193565b610973565b604051610176969594939291906112b2565b61016a61029e3660046111e5565b610a50565b61016a6102b13660046111e5565b610a68565b61016a6102c43660046111fe565b610a7c565b6102dc6102d73660046111b3565b610aa4565b60405161017691906112f6565b6101c95f81565b6101c95f805160206115e983398151915281565b6101926103123660046111e5565b610bfb565b6101c95f8051602061160983398151915281565b6101926103393660046111fe565b610c63565b61035161034c366004611193565b610c87565b60405161017691906113a2565b6101c95f805160206115c983398151915281565b6101926103803660046113dd565b610d99565b5f6001600160e01b03198216637965db0b60e01b14806103b557506301ffc9a760e01b6001600160e01b03198316145b92915050565b5f805160206115c98339815191526103d281610f38565b5f8481526001602052604081208054859081106103f1576103f1611443565b5f918252602090912060069091020190506002600582015460ff16600381111561041d5761041d61127e565b0361046f5760405162461bcd60e51b815260206004820152601b60248201527f4d696c6573746f6e6520616c7265616479207375626d6974746564000000000060448201526064015b60405180910390fd5b80600401544211156104c35760405162461bcd60e51b815260206004820152601d60248201527f4d696c6573746f6e6520646561646c696e6520686173207061737365640000006044820152606401610466565b604080516020808201889052818301879052825180830384018152606090920190925280519101205f9060408051606081018252878152602080820188815242838501525f858152600290925292902081518155915192935091600182019061052c90826114da565b5060409182015160029091015560058301805460ff19166001179055513390869088907f514888eb1a7c1fae361e760cbec737733349d30668d4a8c42659a829a534c76a9061057c908990611595565b60405180910390a4505050505050565b5f805160206115e98339815191526105a381610f38565b5f8381526001602052604081208054849081106105c2576105c2611443565b5f918252602090912060069091020190506002600582015460ff1660038111156105ee576105ee61127e565b0361063b5760405162461bcd60e51b815260206004820152601a60248201527f4d696c6573746f6e6520616c726561647920617070726f7665640000000000006044820152606401610466565b60058101805460ff191660021790556040513390849086907f9ffbb52f2a1362273cd74689a516ca4f52c4282dc13e2e68b7e0646a9a7af7a3905f90a450505050565b5f61068881610f38565b61069f5f8051602061160983398151915283610f45565b506040515f80516020611609833981519152906001600160a01b038416907f0666448aae9b9c8249fd8213a35a9c10218a0eac2fb39b96ed90c67214c7e761905f90a35050565b5f6103b55f8051602061160983398151915283610a7c565b5f8281526020819052604090206001015461071881610f38565b6107228383610f45565b50505050565b6001600160a01b03811633146107515760405163334bd91960e11b815260040160405180910390fd5b61075b8282610fd4565b505050565b5f61076a81610f38565b6107815f805160206115e983398151915283610f45565b506040515f805160206115e9833981519152906001600160a01b038416907f0666448aae9b9c8249fd8213a35a9c10218a0eac2fb39b96ed90c67214c7e761905f90a35050565b60026020525f9081526040902080546001820180549192916107e990611457565b80601f016020809104026020016040519081016040528092919081815260200182805461081590611457565b80156108605780601f1061083757610100808354040283529160200191610860565b820191905f5260205f20905b81548152906001019060200180831161084357829003601f168201915b5050505050908060020154905083565b5f805160206115e983398151915261088781610f38565b5f8481526001602052604081208054859081106108a6576108a6611443565b5f918252602090912060069091020190506002600582015460ff1660038111156108d2576108d261127e565b0361091f5760405162461bcd60e51b815260206004820152601a60248201527f4d696c6573746f6e6520616c726561647920617070726f7665640000000000006044820152606401610466565b60058101805460ff191660031790556040513390859087907f4dfc84da87020df96522cd1be73d8e0a2d933702208e9a44d3f7358ed88a9cd390610964908890611595565b60405180910390a45050505050565b6001602052815f5260405f20818154811061098c575f80fd5b905f5260205f2090600602015f9150915050805f0154908060010154908060020180546109b890611457565b80601f01602080910402602001604051908101604052809291908181526020018280546109e490611457565b8015610a2f5780601f10610a0657610100808354040283529160200191610a2f565b820191905f5260205f20905b815481529060010190602001808311610a1257829003601f168201915b50505050600383015460048401546005909401549293909290915060ff1686565b5f6103b55f805160206115c983398151915283610a7c565b5f6103b55f805160206115e9833981519152835b5f918252602082815260408084206001600160a01b0393909316845291905290205460ff1690565b606060015f8381526020019081526020015f20805480602002602001604051908101604052809291908181526020015f905b82821015610bf0578382905f5260205f2090600602016040518060c00160405290815f820154815260200160018201548152602001600282018054610b1a90611457565b80601f0160208091040260200160405190810160405280929190818152602001828054610b4690611457565b8015610b915780601f10610b6857610100808354040283529160200191610b91565b820191905f5260205f20905b815481529060010190602001808311610b7457829003601f168201915b5050509183525050600382810154602083015260048301546040830152600583015460609092019160ff1690811115610bcc57610bcc61127e565b6003811115610bdd57610bdd61127e565b8152505081526020019060010190610ad6565b505050509050919050565b5f610c0581610f38565b610c1c5f805160206115c983398151915283610f45565b506040515f805160206115c9833981519152906001600160a01b038416907f0666448aae9b9c8249fd8213a35a9c10218a0eac2fb39b96ed90c67214c7e761905f90a35050565b5f82815260208190526040902060010154610c7d81610f38565b6107228383610fd4565b610ca960405180606001604052805f8152602001606081526020015f81525090565b6040805160208082018690528183018590528251808303840181526060909201909252805191012060025f8281526020019081526020015f206040518060600160405290815f8201548152602001600182018054610d0690611457565b80601f0160208091040260200160405190810160405280929190818152602001828054610d3290611457565b8015610d7d5780601f10610d5457610100808354040283529160200191610d7d565b820191905f5260205f20905b815481529060010190602001808311610d6057829003601f168201915b5050505050815260200160028201548152505091505092915050565b610db05f805160206115e983398151915282610a7c565b610dfc5760405162461bcd60e51b815260206004820152601f60248201527f43616c6c6572206d757374206861766520636f6d6d697474656520726f6c65006044820152606401610466565b5f8581526001602052604081205415610e22575f86815260016020526040902054610e24565b5f5b90505f6040518060c001604052808381526020018881526020018781526020018681526020018581526020015f6003811115610e6257610e6261127e565b90525f88815260016020818152604080842080548085018255908552938290208551600690950201938455908401519183019190915582015191925082916002820190610eaf90826114da565b50606082015181600301556080820151816004015560a0820151816005015f6101000a81548160ff02191690836003811115610eed57610eed61127e565b0217905550505081877fdb69cd106eb6f7f884a1210c362d72e26df42e3141159144bd689bd5e0dd582a8888604051610f279291906115a7565b60405180910390a350505050505050565b610f42813361103d565b50565b5f610f508383610a7c565b610fcd575f838152602081815260408083206001600160a01b03861684529091529020805460ff19166001179055610f853390565b6001600160a01b0316826001600160a01b0316847f2f8788117e7eff1d82e926ec794901d17c78024a50270940304540a733656f0d60405160405180910390a45060016103b5565b505f6103b5565b5f610fdf8383610a7c565b15610fcd575f838152602081815260408083206001600160a01b0386168085529252808320805460ff1916905551339286917ff6391f5c32d9c69d2a47ea670b442974b53935d1edc7fd64eb21e047a839171b9190a45060016103b5565b6110478282610a7c565b6110765760405163e2517d3f60e01b81526001600160a01b038216600482015260248101839052604401610466565b5050565b5f6020828403121561108a575f80fd5b81356001600160e01b0319811681146110a1575f80fd5b9392505050565b634e487b7160e01b5f52604160045260245ffd5b5f82601f8301126110cb575f80fd5b813567ffffffffffffffff8111156110e5576110e56110a8565b604051601f8201601f19908116603f0116810167ffffffffffffffff81118282101715611114576111146110a8565b60405281815283820160200185101561112b575f80fd5b816020850160208301375f918101602001919091529392505050565b5f805f60608486031215611159575f80fd5b8335925060208401359150604084013567ffffffffffffffff81111561117d575f80fd5b611189868287016110bc565b9150509250925092565b5f80604083850312156111a4575f80fd5b50508035926020909101359150565b5f602082840312156111c3575f80fd5b5035919050565b80356001600160a01b03811681146111e0575f80fd5b919050565b5f602082840312156111f5575f80fd5b6110a1826111ca565b5f806040838503121561120f575f80fd5b8235915061121f602084016111ca565b90509250929050565b5f81518084528060208401602086015e5f602082860101526020601f19601f83011685010191505092915050565b838152606060208201525f61126e6060830185611228565b9050826040830152949350505050565b634e487b7160e01b5f52602160045260245ffd5b600481106112ae57634e487b7160e01b5f52602160045260245ffd5b9052565b86815285602082015260c060408201525f6112d060c0830187611228565b90508460608301528360808301526112eb60a0830184611292565b979650505050505050565b5f602082016020835280845180835260408501915060408160051b8601019250602086015f5b8281101561139657603f1987860301845281518051865260208101516020870152604081015160c0604088015261135660c0880182611228565b9050606082015160608801526080820151608088015260a0820151915061138060a0880183611292565b955050602093840193919091019060010161131c565b50929695505050505050565b60208152815160208201525f6020830151606060408401526113c76080840182611228565b9050604084015160608401528091505092915050565b5f805f805f60a086880312156113f1575f80fd5b85359450602086013567ffffffffffffffff81111561140e575f80fd5b61141a888289016110bc565b9450506040860135925060608601359150611437608087016111ca565b90509295509295909350565b634e487b7160e01b5f52603260045260245ffd5b600181811c9082168061146b57607f821691505b60208210810361148957634e487b7160e01b5f52602260045260245ffd5b50919050565b601f82111561075b57805f5260205f20601f840160051c810160208510156114b45750805b601f840160051c820191505b818110156114d3575f81556001016114c0565b5050505050565b815167ffffffffffffffff8111156114f4576114f46110a8565b611508816115028454611457565b8461148f565b6020601f82116001811461153a575f83156115235750848201515b5f19600385901b1c1916600184901b1784556114d3565b5f84815260208120601f198516915b828110156115695787850151825560209485019460019092019101611549565b508482101561158657868401515f19600387901b60f8161c191681555b50505050600190811b01905550565b602081525f6110a16020830184611228565b604081525f6115b96040830185611228565b9050826020830152939250505056fe36a5c4aaacb6b388bbd448bf11096b7dafc5652bcc9046084fd0e95b1fb0b2cc794daa56950487582951e8db2fdbcbee68c2223c65641d0aa02a3afc64f9a86f7590f0264744cd50915976d360e18170bc29db6f1a196d3b1061f7f194052170a264697066735822122081b7635bee759f0a2ede1f411c15112e1b86367ebb305d5b52ae37cac960255164736f6c634300081a0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_COMMITTEE_ROLE = "COMMITTEE_ROLE";

    public static final String FUNC_DEFAULT_ADMIN_ROLE = "DEFAULT_ADMIN_ROLE";

    public static final String FUNC_DONOR_ROLE = "DONOR_ROLE";

    public static final String FUNC_STUDENT_ROLE = "STUDENT_ROLE";

    public static final String FUNC_ADDCOMMITTEE = "addCommittee";

    public static final String FUNC_ADDDONOR = "addDonor";

    public static final String FUNC_ADDSTUDENT = "addStudent";

    public static final String FUNC_APPROVEMILESTONE = "approveMilestone";

    public static final String FUNC_CREATEMILESTONEPLAN = "createMilestonePlan";

    public static final String FUNC_GETMILESTONEPLANSBYPROPOSAL = "getMilestonePlansByProposal";

    public static final String FUNC_GETMILESTONESUBMISSION = "getMilestoneSubmission";

    public static final String FUNC_GETROLEADMIN = "getRoleAdmin";

    public static final String FUNC_GRANTROLE = "grantRole";

    public static final String FUNC_HASROLE = "hasRole";

    public static final String FUNC_ISCOMMITTEE = "isCommittee";

    public static final String FUNC_ISDONOR = "isDonor";

    public static final String FUNC_ISSTUDENT = "isStudent";

    public static final String FUNC_MILESTONEPLANS = "milestonePlans";

    public static final String FUNC_MILESTONESUBMISSIONS = "milestoneSubmissions";

    public static final String FUNC_REJECTMILESTONE = "rejectMilestone";

    public static final String FUNC_RENOUNCEROLE = "renounceRole";

    public static final String FUNC_REVOKEROLE = "revokeRole";

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

    public static final Event ROLEASSIGNED_EVENT = new Event("RoleAssigned", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Bytes32>(true) {}));
    ;

    public static final Event ROLEGRANTED_EVENT = new Event("RoleGranted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event ROLEREVOKED_EVENT = new Event("RoleRevoked", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Bytes32>(true) {}));
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

    public static List<RoleAssignedEventResponse> getRoleAssignedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(ROLEASSIGNED_EVENT, transactionReceipt);
        ArrayList<RoleAssignedEventResponse> responses = new ArrayList<RoleAssignedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RoleAssignedEventResponse typedResponse = new RoleAssignedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.role = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static RoleAssignedEventResponse getRoleAssignedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ROLEASSIGNED_EVENT, log);
        RoleAssignedEventResponse typedResponse = new RoleAssignedEventResponse();
        typedResponse.log = log;
        typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.role = (byte[]) eventValues.getIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<RoleAssignedEventResponse> roleAssignedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getRoleAssignedEventFromLog(log));
    }

    public Flowable<RoleAssignedEventResponse> roleAssignedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ROLEASSIGNED_EVENT));
        return roleAssignedEventFlowable(filter);
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
            typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.role = (byte[]) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static RoleRevokedEventResponse getRoleRevokedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(ROLEREVOKED_EVENT, log);
        RoleRevokedEventResponse typedResponse = new RoleRevokedEventResponse();
        typedResponse.log = log;
        typedResponse.user = (String) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.role = (byte[]) eventValues.getIndexedValues().get(1).getValue();
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

    public RemoteFunctionCall<byte[]> DONOR_ROLE() {
        final Function function = new Function(FUNC_DONOR_ROLE, 
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

    public RemoteFunctionCall<TransactionReceipt> addDonor(String donor) {
        final Function function = new Function(
                FUNC_ADDDONOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, donor)), 
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

    public RemoteFunctionCall<Boolean> isCommittee(String user) {
        final Function function = new Function(FUNC_ISCOMMITTEE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, user)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isDonor(String user) {
        final Function function = new Function(FUNC_ISDONOR, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, user)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<Boolean> isStudent(String user) {
        final Function function = new Function(FUNC_ISSTUDENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, user)), 
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

    public RemoteFunctionCall<TransactionReceipt> revokeRole(byte[] role, String account) {
        final Function function = new Function(
                FUNC_REVOKEROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.web3j.abi.datatypes.Address(160, account)), 
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

    public static class RoleAssignedEventResponse extends BaseEventResponse {
        public String user;

        public byte[] role;
    }

    public static class RoleGrantedEventResponse extends BaseEventResponse {
        public byte[] role;

        public String account;

        public String sender;
    }

    public static class RoleRevokedEventResponse extends BaseEventResponse {
        public String user;

        public byte[] role;
    }

    public static class RoleRevokedEventResponse extends BaseEventResponse {
        public byte[] role;

        public String account;

        public String sender;
    }
}
