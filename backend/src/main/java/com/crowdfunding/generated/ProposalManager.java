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
public class ProposalManager extends Contract {
    public static final String BINARY = "608060405234801561000f575f80fd5b50604051611d36380380611d3683398101604081905261002e91610125565b816100395f82610061565b5050600180546001600160a01b0319166001600160a01b039290921691909117905550610156565b5f828152602081815260408083206001600160a01b038516845290915281205460ff16610101575f838152602081815260408083206001600160a01b03861684529091529020805460ff191660011790556100b93390565b6001600160a01b0316826001600160a01b0316847f2f8788117e7eff1d82e926ec794901d17c78024a50270940304540a733656f0d60405160405180910390a4506001610104565b505f5b92915050565b80516001600160a01b0381168114610120575f80fd5b919050565b5f8060408385031215610136575f80fd5b61013f8361010a565b915061014d6020840161010a565b90509250929050565b611bd3806101635f395ff3fe608060405234801561000f575f80fd5b506004361061013d575f3560e01c806391d14854116100b4578063cceb68f511610079578063cceb68f5146102e2578063d547741f146102ea578063da35c664146102fd578063de8ec63f14610306578063eb62a59c14610319578063f7be43fb1461032c575f80fd5b806391d1485414610281578063a217fddf14610294578063ad32563d1461029b578063bd06ea19146102af578063c7f758a8146102c2575f80fd5b806336568abe1161010557806336568abe1461020257806338046217146102155780634957450b146102285780634c5e425a1461024857806356c113671461025b578063707b85271461026e575f80fd5b8063013cf08b1461014157806301ffc9a71461016f5780630b7d662f14610192578063248a9ca3146101bd5780632f2ff15d146101ed575b5f80fd5b61015461014f36600461143d565b610340565b604051610166969594939291906114b6565b60405180910390f35b61018261017d36600461151f565b610516565b6040519015158152602001610166565b6001546101a5906001600160a01b031681565b6040516001600160a01b039091168152602001610166565b6101df6101cb36600461143d565b5f9081526020819052604090206001015490565b604051908152602001610166565b6102006101fb366004611568565b61054c565b005b610200610210366004611568565b610576565b610200610223366004611592565b6105ae565b61023b610236366004611592565b6105cf565b604051610166919061162b565b61020061025636600461173d565b6108f9565b610200610269366004611592565b610a2c565b61020061027c3660046117c8565b610a4d565b61018261028f366004611568565b610b91565b6101df5f81565b6101df5f80516020611b7e83398151915281565b6102006102bd366004611592565b610bb9565b6102d56102d036600461143d565b610bda565b60405161016691906117f9565b61023b610e05565b6102006102f8366004611568565b6110b3565b6101df60035481565b610200610314366004611592565b6110d7565b610200610327366004611897565b6110f8565b6101df5f80516020611b5e83398151915281565b60026020525f908152604090208054600182018054919291610361906119a2565b80601f016020809104026020016040519081016040528092919081815260200182805461038d906119a2565b80156103d85780601f106103af576101008083540402835291602001916103d8565b820191905f5260205f20905b8154815290600101906020018083116103bb57829003601f168201915b5050505050908060020180546103ed906119a2565b80601f0160208091040260200160405190810160405280929190818152602001828054610419906119a2565b80156104645780601f1061043b57610100808354040283529160200191610464565b820191905f5260205f20905b81548152906001019060200180831161044757829003601f168201915b505050505090806003018054610479906119a2565b80601f01602080910402602001604051908101604052809291908181526020018280546104a5906119a2565b80156104f05780601f106104c7576101008083540402835291602001916104f0565b820191905f5260205f20905b8154815290600101906020018083116104d357829003601f168201915b505050600490930154919250506001600160a01b0381169060ff600160a01b9091041686565b5f6001600160e01b03198216637965db0b60e01b148061054657506301ffc9a760e01b6001600160e01b03198316145b92915050565b5f82815260208190526040902060010154610566816112af565b61057083836112bc565b50505050565b6001600160a01b038116331461059f5760405163334bd91960e11b815260040160405180910390fd5b6105a9828261134b565b505050565b5f6105b8816112af565b6105a95f80516020611b7e833981519152836112bc565b60605f805b600354811015610617575f818152600260205260409020600401546001600160a01b0380861691160361060f578161060b816119d4565b9250505b6001016105d4565b505f816001600160401b038111156106315761063161168e565b60405190808252806020026020018201604052801561066a57816020015b6106576113f1565b81526020019060019003908161064f5790505b5090505f805b6003548110156108ef575f818152600260205260409020600401546001600160a01b038088169116036108e75760025f8281526020019081526020015f206040518060c00160405290815f82015481526020016001820180546106d2906119a2565b80601f01602080910402602001604051908101604052809291908181526020018280546106fe906119a2565b80156107495780601f1061072057610100808354040283529160200191610749565b820191905f5260205f20905b81548152906001019060200180831161072c57829003601f168201915b50505050508152602001600282018054610762906119a2565b80601f016020809104026020016040519081016040528092919081815260200182805461078e906119a2565b80156107d95780601f106107b0576101008083540402835291602001916107d9565b820191905f5260205f20905b8154815290600101906020018083116107bc57829003601f168201915b505050505081526020016003820180546107f2906119a2565b80601f016020809104026020016040519081016040528092919081815260200182805461081e906119a2565b80156108695780601f1061084057610100808354040283529160200191610869565b820191905f5260205f20905b81548152906001019060200180831161084c57829003601f168201915b505050918352505060048201546001600160a01b0381166020830152604090910190600160a01b900460ff1660028111156108a6576108a6611482565b60028111156108b7576108b7611482565b815250508383815181106108cd576108cd6119f8565b602002602001018190525081806108e3906119d4565b9250505b600101610670565b5090949350505050565b5f80516020611b5e833981519152610910816112af565b6040805160c0810182526003548082526020808301888152838501889052606084018790523360808501525f60a08501819052928352600290915292902081518155915190919060018201906109669082611a57565b506040820151600282019061097b9082611a57565b50606082015160038201906109909082611a57565b5060808201516004820180546001600160a01b039092166001600160a01b031983168117825560a0850151926001600160a81b03191617600160a01b8360028111156109de576109de611482565b0217905550506003546040513392507f11641d364e926d2bf231c2d36f2a9271390042d717ea334a3e7c983c27a12391905f90a360038054905f610a21836119d4565b919050555050505050565b5f610a36816112af565b6105a95f80516020611b7e8339815191528361134b565b5f80516020611b7e833981519152610a64816112af565b5f838152600260208190526040822060040154600160a01b900460ff1690811115610a9157610a91611482565b14610af75760405162461bcd60e51b815260206004820152602b60248201527f43616e206f6e6c79206368616e676520737461747573206f662070656e64696e60448201526a672070726f706f73616c7360a81b60648201526084015b60405180910390fd5b5f8381526002602081905260409091206004018054849260ff60a01b1990911690600160a01b908490811115610b2f57610b2f611482565b02179055505f83815260026020526040908190206004015490516001600160a01b039091169084907f2284b779e386d2a0b3e08aaf2ab607f5d57b0485f3ef76279c95b5cec1eb778190610b84908690611b11565b60405180910390a3505050565b5f918252602082815260408084206001600160a01b0393909316845291905290205460ff1690565b5f610bc3816112af565b6105a95f80516020611b5e833981519152836112bc565b610be26113f1565b60025f8381526020019081526020015f206040518060c00160405290815f8201548152602001600182018054610c17906119a2565b80601f0160208091040260200160405190810160405280929190818152602001828054610c43906119a2565b8015610c8e5780601f10610c6557610100808354040283529160200191610c8e565b820191905f5260205f20905b815481529060010190602001808311610c7157829003601f168201915b50505050508152602001600282018054610ca7906119a2565b80601f0160208091040260200160405190810160405280929190818152602001828054610cd3906119a2565b8015610d1e5780601f10610cf557610100808354040283529160200191610d1e565b820191905f5260205f20905b815481529060010190602001808311610d0157829003601f168201915b50505050508152602001600382018054610d37906119a2565b80601f0160208091040260200160405190810160405280929190818152602001828054610d63906119a2565b8015610dae5780601f10610d8557610100808354040283529160200191610dae565b820191905f5260205f20905b815481529060010190602001808311610d9157829003601f168201915b505050918352505060048201546001600160a01b0381166020830152604090910190600160a01b900460ff166002811115610deb57610deb611482565b6002811115610dfc57610dfc611482565b90525092915050565b60605f6003546001600160401b03811115610e2257610e2261168e565b604051908082528060200260200182016040528015610e5b57816020015b610e486113f1565b815260200190600190039081610e405790505b5090505f5b6003548110156110ad5760025f8281526020019081526020015f206040518060c00160405290815f8201548152602001600182018054610e9f906119a2565b80601f0160208091040260200160405190810160405280929190818152602001828054610ecb906119a2565b8015610f165780601f10610eed57610100808354040283529160200191610f16565b820191905f5260205f20905b815481529060010190602001808311610ef957829003601f168201915b50505050508152602001600282018054610f2f906119a2565b80601f0160208091040260200160405190810160405280929190818152602001828054610f5b906119a2565b8015610fa65780601f10610f7d57610100808354040283529160200191610fa6565b820191905f5260205f20905b815481529060010190602001808311610f8957829003601f168201915b50505050508152602001600382018054610fbf906119a2565b80601f0160208091040260200160405190810160405280929190818152602001828054610feb906119a2565b80156110365780601f1061100d57610100808354040283529160200191611036565b820191905f5260205f20905b81548152906001019060200180831161101957829003601f168201915b505050918352505060048201546001600160a01b0381166020830152604090910190600160a01b900460ff16600281111561107357611073611482565b600281111561108457611084611482565b8152505082828151811061109a5761109a6119f8565b6020908102919091010152600101610e60565b50919050565b5f828152602081905260409020600101546110cd816112af565b610570838361134b565b5f6110e1816112af565b6105a95f80516020611b5e8339815191528361134b565b5f80516020611b7e83398151915261110f816112af565b60015f86815260026020819052604090912060040154600160a01b900460ff169081111561113f5761113f611482565b146111845760405162461bcd60e51b8152602060048201526015602482015274141c9bdc1bdcd85b081b9bdd08185c1c1c9bdd9959605a1b6044820152606401610aee565b82518451148015611196575081518351145b6111db5760405162461bcd60e51b8152602060048201526016602482015275082e4e4c2f240d8cadccee8d0e640dad2e6dac2e8c6d60531b6044820152606401610aee565b5f5b84518110156112a75760015485516001600160a01b039091169063f9643a0c908890889085908110611211576112116119f8565b602002602001015187858151811061122b5761122b6119f8565b6020026020010151878681518110611245576112456119f8565b6020026020010151336040518663ffffffff1660e01b815260040161126e959493929190611b1f565b5f604051808303815f87803b158015611285575f80fd5b505af1158015611297573d5f803e3d5ffd5b5050600190920191506111dd9050565b505050505050565b6112b981336113b4565b50565b5f6112c78383610b91565b611344575f838152602081815260408083206001600160a01b03861684529091529020805460ff191660011790556112fc3390565b6001600160a01b0316826001600160a01b0316847f2f8788117e7eff1d82e926ec794901d17c78024a50270940304540a733656f0d60405160405180910390a4506001610546565b505f610546565b5f6113568383610b91565b15611344575f838152602081815260408083206001600160a01b0386168085529252808320805460ff1916905551339286917ff6391f5c32d9c69d2a47ea670b442974b53935d1edc7fd64eb21e047a839171b9190a4506001610546565b6113be8282610b91565b6113ed5760405163e2517d3f60e01b81526001600160a01b038216600482015260248101839052604401610aee565b5050565b6040518060c001604052805f81526020016060815260200160608152602001606081526020015f6001600160a01b031681526020015f600281111561143857611438611482565b905290565b5f6020828403121561144d575f80fd5b5035919050565b5f81518084528060208401602086015e5f602082860101526020601f19601f83011685010191505092915050565b634e487b7160e01b5f52602160045260245ffd5b600381106114b257634e487b7160e01b5f52602160045260245ffd5b9052565b86815260c060208201525f6114ce60c0830188611454565b82810360408401526114e08188611454565b905082810360608401526114f48187611454565b6001600160a01b03861660808501529150611514905060a0830184611496565b979650505050505050565b5f6020828403121561152f575f80fd5b81356001600160e01b031981168114611546575f80fd5b9392505050565b80356001600160a01b0381168114611563575f80fd5b919050565b5f8060408385031215611579575f80fd5b823591506115896020840161154d565b90509250929050565b5f602082840312156115a2575f80fd5b6115468261154d565b805182525f602082015160c060208501526115c960c0850182611454565b9050604083015184820360408601526115e28282611454565b915050606083015184820360608601526115fc8282611454565b91505060018060a01b03608084015116608085015260a083015161162360a0860182611496565b509392505050565b5f602082016020835280845180835260408501915060408160051b8601019250602086015f5b8281101561168257603f1987860301845261166d8583516115ab565b94506020938401939190910190600101611651565b50929695505050505050565b634e487b7160e01b5f52604160045260245ffd5b604051601f8201601f191681016001600160401b03811182821017156116ca576116ca61168e565b604052919050565b5f82601f8301126116e1575f80fd5b81356001600160401b038111156116fa576116fa61168e565b61170d601f8201601f19166020016116a2565b818152846020838601011115611721575f80fd5b816020850160208301375f918101602001919091529392505050565b5f805f6060848603121561174f575f80fd5b83356001600160401b03811115611764575f80fd5b611770868287016116d2565b93505060208401356001600160401b0381111561178b575f80fd5b611797868287016116d2565b92505060408401356001600160401b038111156117b2575f80fd5b6117be868287016116d2565b9150509250925092565b5f80604083850312156117d9575f80fd5b823591506020830135600381106117ee575f80fd5b809150509250929050565b602081525f61154660208301846115ab565b5f6001600160401b038211156118235761182361168e565b5060051b60200190565b5f82601f83011261183c575f80fd5b813561184f61184a8261180b565b6116a2565b8082825260208201915060208360051b860101925085831115611870575f80fd5b602085015b8381101561188d578035835260209283019201611875565b5095945050505050565b5f805f80608085870312156118aa575f80fd5b8435935060208501356001600160401b038111156118c6575f80fd5b8501601f810187136118d6575f80fd5b80356118e461184a8261180b565b8082825260208201915060208360051b850101925089831115611905575f80fd5b602084015b838110156119455780356001600160401b03811115611927575f80fd5b6119368c6020838901016116d2565b8452506020928301920161190a565b50955050505060408501356001600160401b03811115611963575f80fd5b61196f8782880161182d565b92505060608501356001600160401b0381111561198a575f80fd5b6119968782880161182d565b91505092959194509250565b600181811c908216806119b657607f821691505b6020821081036110ad57634e487b7160e01b5f52602260045260245ffd5b5f600182016119f157634e487b7160e01b5f52601160045260245ffd5b5060010190565b634e487b7160e01b5f52603260045260245ffd5b601f8211156105a957805f5260205f20601f840160051c81016020851015611a315750805b601f840160051c820191505b81811015611a50575f8155600101611a3d565b5050505050565b81516001600160401b03811115611a7057611a7061168e565b611a8481611a7e84546119a2565b84611a0c565b6020601f821160018114611ab6575f8315611a9f5750848201515b5f19600385901b1c1916600184901b178455611a50565b5f84815260208120601f198516915b82811015611ae55787850151825560209485019460019092019101611ac5565b5084821015611b0257868401515f19600387901b60f8161c191681555b50505050600190811b01905550565b602081016105468284611496565b85815260a060208201525f611b3760a0830187611454565b60408301959095525060608101929092526001600160a01b03166080909101529291505056fe36a5c4aaacb6b388bbd448bf11096b7dafc5652bcc9046084fd0e95b1fb0b2cc794daa56950487582951e8db2fdbcbee68c2223c65641d0aa02a3afc64f9a86fa2646970667358221220a464aa42570d038dec10518c1903d1a44117723294051f30b8c762b067f94dc464736f6c634300081a0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_COMMITTEE_ROLE = "COMMITTEE_ROLE";

    public static final String FUNC_DEFAULT_ADMIN_ROLE = "DEFAULT_ADMIN_ROLE";

    public static final String FUNC_STUDENT_ROLE = "STUDENT_ROLE";

    public static final String FUNC_ADDCOMMITTEE = "addCommittee";

    public static final String FUNC_ADDSTUDENT = "addStudent";

    public static final String FUNC_CHANGEPROPOSALSTATUS = "changeProposalStatus";

    public static final String FUNC_CREATEMILESTONESFORPROPOSAL = "createMilestonesForProposal";

    public static final String FUNC_GETALLPROPOSALS = "getAllProposals";

    public static final String FUNC_GETPROPOSAL = "getProposal";

    public static final String FUNC_GETPROPOSALSBYSTUDENT = "getProposalsByStudent";

    public static final String FUNC_GETROLEADMIN = "getRoleAdmin";

    public static final String FUNC_GRANTROLE = "grantRole";

    public static final String FUNC_HASROLE = "hasRole";

    public static final String FUNC_MILESTONEMANAGER = "milestoneManager";

    public static final String FUNC_PROPOSALCOUNT = "proposalCount";

    public static final String FUNC_PROPOSALS = "proposals";

    public static final String FUNC_RENOUNCEROLE = "renounceRole";

    public static final String FUNC_REVOKECOMMITTEE = "revokeCommittee";

    public static final String FUNC_REVOKEROLE = "revokeRole";

    public static final String FUNC_REVOKESTUDENT = "revokeStudent";

    public static final String FUNC_SUBMITPROPOSAL = "submitProposal";

    public static final String FUNC_SUPPORTSINTERFACE = "supportsInterface";

    public static final Event PROPOSALSTATUSCHANGED_EVENT = new Event("ProposalStatusChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint8>() {}));
    ;

    public static final Event PROPOSALSUBMITTED_EVENT = new Event("ProposalSubmitted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Address>(true) {}));
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
    protected ProposalManager(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ProposalManager(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ProposalManager(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ProposalManager(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ProposalStatusChangedEventResponse> getProposalStatusChangedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PROPOSALSTATUSCHANGED_EVENT, transactionReceipt);
        ArrayList<ProposalStatusChangedEventResponse> responses = new ArrayList<ProposalStatusChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ProposalStatusChangedEventResponse typedResponse = new ProposalStatusChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.student = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.newStatus = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ProposalStatusChangedEventResponse getProposalStatusChangedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PROPOSALSTATUSCHANGED_EVENT, log);
        ProposalStatusChangedEventResponse typedResponse = new ProposalStatusChangedEventResponse();
        typedResponse.log = log;
        typedResponse.id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.student = (String) eventValues.getIndexedValues().get(1).getValue();
        typedResponse.newStatus = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        return typedResponse;
    }

    public Flowable<ProposalStatusChangedEventResponse> proposalStatusChangedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getProposalStatusChangedEventFromLog(log));
    }

    public Flowable<ProposalStatusChangedEventResponse> proposalStatusChangedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PROPOSALSTATUSCHANGED_EVENT));
        return proposalStatusChangedEventFlowable(filter);
    }

    public static List<ProposalSubmittedEventResponse> getProposalSubmittedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PROPOSALSUBMITTED_EVENT, transactionReceipt);
        ArrayList<ProposalSubmittedEventResponse> responses = new ArrayList<ProposalSubmittedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ProposalSubmittedEventResponse typedResponse = new ProposalSubmittedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.student = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ProposalSubmittedEventResponse getProposalSubmittedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PROPOSALSUBMITTED_EVENT, log);
        ProposalSubmittedEventResponse typedResponse = new ProposalSubmittedEventResponse();
        typedResponse.log = log;
        typedResponse.id = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.student = (String) eventValues.getIndexedValues().get(1).getValue();
        return typedResponse;
    }

    public Flowable<ProposalSubmittedEventResponse> proposalSubmittedEventFlowable(
            EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getProposalSubmittedEventFromLog(log));
    }

    public Flowable<ProposalSubmittedEventResponse> proposalSubmittedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PROPOSALSUBMITTED_EVENT));
        return proposalSubmittedEventFlowable(filter);
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

    public RemoteFunctionCall<TransactionReceipt> changeProposalStatus(BigInteger _id,
            BigInteger _newStatus) {
        final Function function = new Function(
                FUNC_CHANGEPROPOSALSTATUS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_id), 
                new org.web3j.abi.datatypes.generated.Uint8(_newStatus)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> createMilestonesForProposal(
            BigInteger _proposalId, List<String> _descriptions, List<BigInteger> _fundingAmounts,
            List<BigInteger> _deadlines) {
        final Function function = new Function(
                FUNC_CREATEMILESTONESFORPROPOSAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_proposalId), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Utf8String>(
                        org.web3j.abi.datatypes.Utf8String.class,
                        org.web3j.abi.Utils.typeMap(_descriptions, org.web3j.abi.datatypes.Utf8String.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(_fundingAmounts, org.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(_deadlines, org.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getAllProposals() {
        final Function function = new Function(FUNC_GETALLPROPOSALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<ProposalDetails>>() {}));
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

    public RemoteFunctionCall<ProposalDetails> getProposal(BigInteger _proposalId) {
        final Function function = new Function(FUNC_GETPROPOSAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_proposalId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<ProposalDetails>() {}));
        return executeRemoteCallSingleValueReturn(function, ProposalDetails.class);
    }

    public RemoteFunctionCall<List> getProposalsByStudent(String _student) {
        final Function function = new Function(FUNC_GETPROPOSALSBYSTUDENT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _student)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<ProposalDetails>>() {}));
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

    public RemoteFunctionCall<String> milestoneManager() {
        final Function function = new Function(FUNC_MILESTONEMANAGER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> proposalCount() {
        final Function function = new Function(FUNC_PROPOSALCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple6<BigInteger, String, String, String, String, BigInteger>> proposals(
            BigInteger param0) {
        final Function function = new Function(FUNC_PROPOSALS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Uint8>() {}));
        return new RemoteFunctionCall<Tuple6<BigInteger, String, String, String, String, BigInteger>>(function,
                new Callable<Tuple6<BigInteger, String, String, String, String, BigInteger>>() {
                    @Override
                    public Tuple6<BigInteger, String, String, String, String, BigInteger> call()
                            throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<BigInteger, String, String, String, String, BigInteger>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (String) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue());
                    }
                });
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

    public RemoteFunctionCall<TransactionReceipt> submitProposal(String _title, String _contentCID,
            String _planCID) {
        final Function function = new Function(
                FUNC_SUBMITPROPOSAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_title), 
                new org.web3j.abi.datatypes.Utf8String(_contentCID), 
                new org.web3j.abi.datatypes.Utf8String(_planCID)), 
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
    public static ProposalManager load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new ProposalManager(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ProposalManager load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ProposalManager(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ProposalManager load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new ProposalManager(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ProposalManager load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ProposalManager(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ProposalManager> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider, String initialAdmin,
            String _milestoneManager) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, initialAdmin), 
                new org.web3j.abi.datatypes.Address(160, _milestoneManager)));
        return deployRemoteCall(ProposalManager.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    public static RemoteCall<ProposalManager> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider,
            String initialAdmin, String _milestoneManager) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, initialAdmin), 
                new org.web3j.abi.datatypes.Address(160, _milestoneManager)));
        return deployRemoteCall(ProposalManager.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ProposalManager> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit, String initialAdmin,
            String _milestoneManager) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, initialAdmin), 
                new org.web3j.abi.datatypes.Address(160, _milestoneManager)));
        return deployRemoteCall(ProposalManager.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ProposalManager> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit,
            String initialAdmin, String _milestoneManager) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, initialAdmin), 
                new org.web3j.abi.datatypes.Address(160, _milestoneManager)));
        return deployRemoteCall(ProposalManager.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), encodedConstructor);
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

    public static class ProposalDetails extends DynamicStruct {
        public BigInteger id;

        public String title;

        public String contentCID;

        public String planCID;

        public String student;

        public BigInteger status;

        public ProposalDetails(BigInteger id, String title, String contentCID, String planCID,
                String student, BigInteger status) {
            super(new org.web3j.abi.datatypes.generated.Uint256(id), 
                    new org.web3j.abi.datatypes.Utf8String(title), 
                    new org.web3j.abi.datatypes.Utf8String(contentCID), 
                    new org.web3j.abi.datatypes.Utf8String(planCID), 
                    new org.web3j.abi.datatypes.Address(160, student), 
                    new org.web3j.abi.datatypes.generated.Uint8(status));
            this.id = id;
            this.title = title;
            this.contentCID = contentCID;
            this.planCID = planCID;
            this.student = student;
            this.status = status;
        }

        public ProposalDetails(Uint256 id, Utf8String title, Utf8String contentCID,
                Utf8String planCID, Address student, Uint8 status) {
            super(id, title, contentCID, planCID, student, status);
            this.id = id.getValue();
            this.title = title.getValue();
            this.contentCID = contentCID.getValue();
            this.planCID = planCID.getValue();
            this.student = student.getValue();
            this.status = status.getValue();
        }
    }

    public static class ProposalStatusChangedEventResponse extends BaseEventResponse {
        public BigInteger id;

        public String student;

        public BigInteger newStatus;
    }

    public static class ProposalSubmittedEventResponse extends BaseEventResponse {
        public BigInteger id;

        public String student;
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
