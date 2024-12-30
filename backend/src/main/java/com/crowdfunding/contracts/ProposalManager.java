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
public class ProposalManager extends Contract {
    public static final String BINARY = "608060405234801561000f575f80fd5b50604051611eb8380380611eb883398101604081905261002e91610125565b816100395f82610061565b5050600180546001600160a01b0319166001600160a01b039290921691909117905550610156565b5f828152602081815260408083206001600160a01b038516845290915281205460ff16610101575f838152602081815260408083206001600160a01b03861684529091529020805460ff191660011790556100b93390565b6001600160a01b0316826001600160a01b0316847f2f8788117e7eff1d82e926ec794901d17c78024a50270940304540a733656f0d60405160405180910390a4506001610104565b505f5b92915050565b80516001600160a01b0381168114610120575f80fd5b919050565b5f8060408385031215610136575f80fd5b61013f8361010a565b915061014d6020840161010a565b90509250929050565b611d55806101635f395ff3fe608060405234801561000f575f80fd5b506004361061016d575f3560e01c806382f7d392116100d9578063c7f758a811610093578063d547741f1161006e578063d547741f14610367578063da35c6641461037a578063eb62a59c14610383578063f7be43fb14610396575f80fd5b8063c7f758a81461032b578063c864be8a1461034b578063cceb68f51461035f575f80fd5b806382f7d392146102c45780638c9540d3146102d757806391d14854146102ea578063a217fddf146102fd578063ad32563d14610304578063bd06ea1914610318575f80fd5b80632f2ff15d1161012a5780632f2ff15d1461024557806336568abe14610258578063380462171461026b5780634957450b1461027e5780634c5e425a1461029e578063707b8527146102b1575f80fd5b8063013cf08b1461017157806301ffc9a71461019f5780630b7d662f146101c2578063248a9ca3146101ed578063257468341461021d5780632bc3d7aa14610232575b5f80fd5b61018461017f36600461159f565b6103aa565b60405161019696959493929190611618565b60405180910390f35b6101b26101ad366004611681565b610580565b6040519015158152602001610196565b6001546101d5906001600160a01b031681565b6040516001600160a01b039091168152602001610196565b61020f6101fb36600461159f565b5f9081526020819052604090206001015490565b604051908152602001610196565b61023061022b3660046116ca565b6105b6565b005b6101b26102403660046116ca565b61061e565b6102306102533660046116e3565b610636565b6102306102663660046116e3565b610660565b6102306102793660046116ca565b610698565b61029161028c3660046116ca565b610700565b604051610196919061178d565b6102306102ac36600461189f565b610a2a565b6102306102bf36600461192a565b610b5d565b6101b26102d23660046116ca565b610ca1565b6101b26102e53660046116ca565b610cb9565b6101b26102f83660046116e3565b610ccd565b61020f5f81565b61020f5f80516020611ce083398151915281565b6102306103263660046116ca565b610cf5565b61033e61033936600461159f565b610d5d565b604051610196919061195b565b61020f5f80516020611d0083398151915281565b610291610f88565b6102306103753660046116e3565b611236565b61020f60035481565b6102306103913660046119f9565b61125a565b61020f5f80516020611cc083398151915281565b60026020525f9081526040902080546001820180549192916103cb90611b04565b80601f01602080910402602001604051908101604052809291908181526020018280546103f790611b04565b80156104425780601f1061041957610100808354040283529160200191610442565b820191905f5260205f20905b81548152906001019060200180831161042557829003601f168201915b50505050509080600201805461045790611b04565b80601f016020809104026020016040519081016040528092919081815260200182805461048390611b04565b80156104ce5780601f106104a5576101008083540402835291602001916104ce565b820191905f5260205f20905b8154815290600101906020018083116104b157829003601f168201915b5050505050908060030180546104e390611b04565b80601f016020809104026020016040519081016040528092919081815260200182805461050f90611b04565b801561055a5780601f106105315761010080835404028352916020019161055a565b820191905f5260205f20905b81548152906001019060200180831161053d57829003601f168201915b505050600490930154919250506001600160a01b0381169060ff600160a01b9091041686565b5f6001600160e01b03198216637965db0b60e01b14806105b057506301ffc9a760e01b6001600160e01b03198316145b92915050565b5f6105c081611411565b6105d75f80516020611d008339815191528361141e565b506040515f80516020611d00833981519152906001600160a01b038416907f0666448aae9b9c8249fd8213a35a9c10218a0eac2fb39b96ed90c67214c7e761905f90a35050565b5f6105b05f80516020611d0083398151915283610ccd565b5f8281526020819052604090206001015461065081611411565b61065a838361141e565b50505050565b6001600160a01b03811633146106895760405163334bd91960e11b815260040160405180910390fd5b61069382826114ad565b505050565b5f6106a281611411565b6106b95f80516020611ce08339815191528361141e565b506040515f80516020611ce0833981519152906001600160a01b038416907f0666448aae9b9c8249fd8213a35a9c10218a0eac2fb39b96ed90c67214c7e761905f90a35050565b60605f805b600354811015610748575f818152600260205260409020600401546001600160a01b03808616911603610740578161073c81611b36565b9250505b600101610705565b505f816001600160401b03811115610762576107626117f0565b60405190808252806020026020018201604052801561079b57816020015b610788611553565b8152602001906001900390816107805790505b5090505f805b600354811015610a20575f818152600260205260409020600401546001600160a01b03808816911603610a185760025f8281526020019081526020015f206040518060c00160405290815f820154815260200160018201805461080390611b04565b80601f016020809104026020016040519081016040528092919081815260200182805461082f90611b04565b801561087a5780601f106108515761010080835404028352916020019161087a565b820191905f5260205f20905b81548152906001019060200180831161085d57829003601f168201915b5050505050815260200160028201805461089390611b04565b80601f01602080910402602001604051908101604052809291908181526020018280546108bf90611b04565b801561090a5780601f106108e15761010080835404028352916020019161090a565b820191905f5260205f20905b8154815290600101906020018083116108ed57829003601f168201915b5050505050815260200160038201805461092390611b04565b80601f016020809104026020016040519081016040528092919081815260200182805461094f90611b04565b801561099a5780601f106109715761010080835404028352916020019161099a565b820191905f5260205f20905b81548152906001019060200180831161097d57829003601f168201915b505050918352505060048201546001600160a01b0381166020830152604090910190600160a01b900460ff1660028111156109d7576109d76115e4565b60028111156109e8576109e86115e4565b815250508383815181106109fe576109fe611b5a565b60200260200101819052508180610a1490611b36565b9250505b6001016107a1565b5090949350505050565b5f80516020611cc0833981519152610a4181611411565b6040805160c0810182526003548082526020808301888152838501889052606084018790523360808501525f60a0850181905292835260029091529290208151815591519091906001820190610a979082611bb9565b5060408201516002820190610aac9082611bb9565b5060608201516003820190610ac19082611bb9565b5060808201516004820180546001600160a01b039092166001600160a01b031983168117825560a0850151926001600160a81b03191617600160a01b836002811115610b0f57610b0f6115e4565b0217905550506003546040513392507f11641d364e926d2bf231c2d36f2a9271390042d717ea334a3e7c983c27a12391905f90a360038054905f610b5283611b36565b919050555050505050565b5f80516020611ce0833981519152610b7481611411565b5f838152600260208190526040822060040154600160a01b900460ff1690811115610ba157610ba16115e4565b14610c075760405162461bcd60e51b815260206004820152602b60248201527f43616e206f6e6c79206368616e676520737461747573206f662070656e64696e60448201526a672070726f706f73616c7360a81b60648201526084015b60405180910390fd5b5f8381526002602081905260409091206004018054849260ff60a01b1990911690600160a01b908490811115610c3f57610c3f6115e4565b02179055505f83815260026020526040908190206004015490516001600160a01b039091169084907f2284b779e386d2a0b3e08aaf2ab607f5d57b0485f3ef76279c95b5cec1eb778190610c94908690611c73565b60405180910390a3505050565b5f6105b05f80516020611cc083398151915283610ccd565b5f6105b05f80516020611ce0833981519152835b5f918252602082815260408084206001600160a01b0393909316845291905290205460ff1690565b5f610cff81611411565b610d165f80516020611cc08339815191528361141e565b506040515f80516020611cc0833981519152906001600160a01b038416907f0666448aae9b9c8249fd8213a35a9c10218a0eac2fb39b96ed90c67214c7e761905f90a35050565b610d65611553565b60025f8381526020019081526020015f206040518060c00160405290815f8201548152602001600182018054610d9a90611b04565b80601f0160208091040260200160405190810160405280929190818152602001828054610dc690611b04565b8015610e115780601f10610de857610100808354040283529160200191610e11565b820191905f5260205f20905b815481529060010190602001808311610df457829003601f168201915b50505050508152602001600282018054610e2a90611b04565b80601f0160208091040260200160405190810160405280929190818152602001828054610e5690611b04565b8015610ea15780601f10610e7857610100808354040283529160200191610ea1565b820191905f5260205f20905b815481529060010190602001808311610e8457829003601f168201915b50505050508152602001600382018054610eba90611b04565b80601f0160208091040260200160405190810160405280929190818152602001828054610ee690611b04565b8015610f315780601f10610f0857610100808354040283529160200191610f31565b820191905f5260205f20905b815481529060010190602001808311610f1457829003601f168201915b505050918352505060048201546001600160a01b0381166020830152604090910190600160a01b900460ff166002811115610f6e57610f6e6115e4565b6002811115610f7f57610f7f6115e4565b90525092915050565b60605f6003546001600160401b03811115610fa557610fa56117f0565b604051908082528060200260200182016040528015610fde57816020015b610fcb611553565b815260200190600190039081610fc35790505b5090505f5b6003548110156112305760025f8281526020019081526020015f206040518060c00160405290815f820154815260200160018201805461102290611b04565b80601f016020809104026020016040519081016040528092919081815260200182805461104e90611b04565b80156110995780601f1061107057610100808354040283529160200191611099565b820191905f5260205f20905b81548152906001019060200180831161107c57829003601f168201915b505050505081526020016002820180546110b290611b04565b80601f01602080910402602001604051908101604052809291908181526020018280546110de90611b04565b80156111295780601f1061110057610100808354040283529160200191611129565b820191905f5260205f20905b81548152906001019060200180831161110c57829003601f168201915b5050505050815260200160038201805461114290611b04565b80601f016020809104026020016040519081016040528092919081815260200182805461116e90611b04565b80156111b95780601f10611190576101008083540402835291602001916111b9565b820191905f5260205f20905b81548152906001019060200180831161119c57829003601f168201915b505050918352505060048201546001600160a01b0381166020830152604090910190600160a01b900460ff1660028111156111f6576111f66115e4565b6002811115611207576112076115e4565b8152505082828151811061121d5761121d611b5a565b6020908102919091010152600101610fe3565b50919050565b5f8281526020819052604090206001015461125081611411565b61065a83836114ad565b5f80516020611ce083398151915261127181611411565b60015f86815260026020819052604090912060040154600160a01b900460ff16908111156112a1576112a16115e4565b146112e65760405162461bcd60e51b8152602060048201526015602482015274141c9bdc1bdcd85b081b9bdd08185c1c1c9bdd9959605a1b6044820152606401610bfe565b825184511480156112f8575081518351145b61133d5760405162461bcd60e51b8152602060048201526016602482015275082e4e4c2f240d8cadccee8d0e640dad2e6dac2e8c6d60531b6044820152606401610bfe565b5f5b84518110156114095760015485516001600160a01b039091169063f9643a0c90889088908590811061137357611373611b5a565b602002602001015187858151811061138d5761138d611b5a565b60200260200101518786815181106113a7576113a7611b5a565b6020026020010151336040518663ffffffff1660e01b81526004016113d0959493929190611c81565b5f604051808303815f87803b1580156113e7575f80fd5b505af11580156113f9573d5f803e3d5ffd5b50506001909201915061133f9050565b505050505050565b61141b8133611516565b50565b5f6114298383610ccd565b6114a6575f838152602081815260408083206001600160a01b03861684529091529020805460ff1916600117905561145e3390565b6001600160a01b0316826001600160a01b0316847f2f8788117e7eff1d82e926ec794901d17c78024a50270940304540a733656f0d60405160405180910390a45060016105b0565b505f6105b0565b5f6114b88383610ccd565b156114a6575f838152602081815260408083206001600160a01b0386168085529252808320805460ff1916905551339286917ff6391f5c32d9c69d2a47ea670b442974b53935d1edc7fd64eb21e047a839171b9190a45060016105b0565b6115208282610ccd565b61154f5760405163e2517d3f60e01b81526001600160a01b038216600482015260248101839052604401610bfe565b5050565b6040518060c001604052805f81526020016060815260200160608152602001606081526020015f6001600160a01b031681526020015f600281111561159a5761159a6115e4565b905290565b5f602082840312156115af575f80fd5b5035919050565b5f81518084528060208401602086015e5f602082860101526020601f19601f83011685010191505092915050565b634e487b7160e01b5f52602160045260245ffd5b6003811061161457634e487b7160e01b5f52602160045260245ffd5b9052565b86815260c060208201525f61163060c08301886115b6565b828103604084015261164281886115b6565b9050828103606084015261165681876115b6565b6001600160a01b03861660808501529150611676905060a08301846115f8565b979650505050505050565b5f60208284031215611691575f80fd5b81356001600160e01b0319811681146116a8575f80fd5b9392505050565b80356001600160a01b03811681146116c5575f80fd5b919050565b5f602082840312156116da575f80fd5b6116a8826116af565b5f80604083850312156116f4575f80fd5b82359150611704602084016116af565b90509250929050565b805182525f602082015160c0602085015261172b60c08501826115b6565b90506040830151848203604086015261174482826115b6565b9150506060830151848203606086015261175e82826115b6565b91505060018060a01b03608084015116608085015260a083015161178560a08601826115f8565b509392505050565b5f602082016020835280845180835260408501915060408160051b8601019250602086015f5b828110156117e457603f198786030184526117cf85835161170d565b945060209384019391909101906001016117b3565b50929695505050505050565b634e487b7160e01b5f52604160045260245ffd5b604051601f8201601f191681016001600160401b038111828210171561182c5761182c6117f0565b604052919050565b5f82601f830112611843575f80fd5b81356001600160401b0381111561185c5761185c6117f0565b61186f601f8201601f1916602001611804565b818152846020838601011115611883575f80fd5b816020850160208301375f918101602001919091529392505050565b5f805f606084860312156118b1575f80fd5b83356001600160401b038111156118c6575f80fd5b6118d286828701611834565b93505060208401356001600160401b038111156118ed575f80fd5b6118f986828701611834565b92505060408401356001600160401b03811115611914575f80fd5b61192086828701611834565b9150509250925092565b5f806040838503121561193b575f80fd5b82359150602083013560038110611950575f80fd5b809150509250929050565b602081525f6116a8602083018461170d565b5f6001600160401b03821115611985576119856117f0565b5060051b60200190565b5f82601f83011261199e575f80fd5b81356119b16119ac8261196d565b611804565b8082825260208201915060208360051b8601019250858311156119d2575f80fd5b602085015b838110156119ef5780358352602092830192016119d7565b5095945050505050565b5f805f8060808587031215611a0c575f80fd5b8435935060208501356001600160401b03811115611a28575f80fd5b8501601f81018713611a38575f80fd5b8035611a466119ac8261196d565b8082825260208201915060208360051b850101925089831115611a67575f80fd5b602084015b83811015611aa75780356001600160401b03811115611a89575f80fd5b611a988c602083890101611834565b84525060209283019201611a6c565b50955050505060408501356001600160401b03811115611ac5575f80fd5b611ad18782880161198f565b92505060608501356001600160401b03811115611aec575f80fd5b611af88782880161198f565b91505092959194509250565b600181811c90821680611b1857607f821691505b60208210810361123057634e487b7160e01b5f52602260045260245ffd5b5f60018201611b5357634e487b7160e01b5f52601160045260245ffd5b5060010190565b634e487b7160e01b5f52603260045260245ffd5b601f82111561069357805f5260205f20601f840160051c81016020851015611b935750805b601f840160051c820191505b81811015611bb2575f8155600101611b9f565b5050505050565b81516001600160401b03811115611bd257611bd26117f0565b611be681611be08454611b04565b84611b6e565b6020601f821160018114611c18575f8315611c015750848201515b5f19600385901b1c1916600184901b178455611bb2565b5f84815260208120601f198516915b82811015611c475787850151825560209485019460019092019101611c27565b5084821015611c6457868401515f19600387901b60f8161c191681555b50505050600190811b01905550565b602081016105b082846115f8565b85815260a060208201525f611c9960a08301876115b6565b60408301959095525060608101929092526001600160a01b03166080909101529291505056fe36a5c4aaacb6b388bbd448bf11096b7dafc5652bcc9046084fd0e95b1fb0b2cc794daa56950487582951e8db2fdbcbee68c2223c65641d0aa02a3afc64f9a86f7590f0264744cd50915976d360e18170bc29db6f1a196d3b1061f7f194052170a2646970667358221220f94d47c01474afbde527ddd7373eeeeee38a0c6e7f2c9f523734b16a92965dbb64736f6c634300081a0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_COMMITTEE_ROLE = "COMMITTEE_ROLE";

    public static final String FUNC_DEFAULT_ADMIN_ROLE = "DEFAULT_ADMIN_ROLE";

    public static final String FUNC_DONOR_ROLE = "DONOR_ROLE";

    public static final String FUNC_STUDENT_ROLE = "STUDENT_ROLE";

    public static final String FUNC_ADDCOMMITTEE = "addCommittee";

    public static final String FUNC_ADDDONOR = "addDonor";

    public static final String FUNC_ADDSTUDENT = "addStudent";

    public static final String FUNC_CHANGEPROPOSALSTATUS = "changeProposalStatus";

    public static final String FUNC_CREATEMILESTONESFORPROPOSAL = "createMilestonesForProposal";

    public static final String FUNC_GETALLPROPOSALS = "getAllProposals";

    public static final String FUNC_GETPROPOSAL = "getProposal";

    public static final String FUNC_GETPROPOSALSBYSTUDENT = "getProposalsByStudent";

    public static final String FUNC_GETROLEADMIN = "getRoleAdmin";

    public static final String FUNC_GRANTROLE = "grantRole";

    public static final String FUNC_HASROLE = "hasRole";

    public static final String FUNC_ISCOMMITTEE = "isCommittee";

    public static final String FUNC_ISDONOR = "isDonor";

    public static final String FUNC_ISSTUDENT = "isStudent";

    public static final String FUNC_MILESTONEMANAGER = "milestoneManager";

    public static final String FUNC_PROPOSALCOUNT = "proposalCount";

    public static final String FUNC_PROPOSALS = "proposals";

    public static final String FUNC_RENOUNCEROLE = "renounceRole";

    public static final String FUNC_REVOKEROLE = "revokeRole";

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

    public RemoteFunctionCall<TransactionReceipt> revokeRole(byte[] role, String account) {
        final Function function = new Function(
                FUNC_REVOKEROLE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(role), 
                new org.web3j.abi.datatypes.Address(160, account)), 
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
