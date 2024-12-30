package com.crowdfunding.service;

import com.crowdfunding.generated.RoleManager;
import com.crowdfunding.security.JwtService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
// import org.web3j.crypto.ECDSASignature;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.security.SignatureException;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    
    private final Web3Service web3Service;
    private final JwtService jwtService;

    public String generateNonce(String address) {
        return "Welcome to Crowdfunding! Please sign this message to verify your wallet.\nNonce: " + 
               System.currentTimeMillis();
    }

    public String verifySignature(String message, String signature, String address) throws SignatureException {
        byte[] messageHash = Hash.sha3(message.getBytes());
        byte[] signatureBytes = Numeric.hexStringToByteArray(signature);
        
        byte v = signatureBytes[64];
        byte[] r = Arrays.copyOfRange(signatureBytes, 0, 32);
        byte[] s = Arrays.copyOfRange(signatureBytes, 32, 64);
        
        Sign.SignatureData signatureData = new Sign.SignatureData(v, r, s);
        BigInteger publicKey = Sign.signedMessageToKey(messageHash, signatureData);
        String recoveredAddress = "0x" + Keys.getAddress(publicKey);
        
        if (!recoveredAddress.equalsIgnoreCase(address)) {
            throw new SignatureException("Invalid signature");
        }

        return determineRole(address);
    }

    private String determineRole(String address) {
        try {
            // AdminManager adminManager = web3Service.loadAdminManager(null);
            RoleManager roleManager = web3Service.loadRoleManager(null);
            if (roleManager.hasRole(roleManager.DEFAULT_ADMIN_ROLE().send(), address).send()) {
                return "ADMIN";
            } else if (roleManager.hasRole(roleManager.COMMITTEE_ROLE().send(), address).send()) {
                return "COMMITTEE";
            } else if (roleManager.hasRole(roleManager.STUDENT_ROLE().send(), address).send()) {
                return "STUDENT";
            } else {
                return "DONOR";
            }
        } catch (Exception e) {
            throw new RuntimeException("Error determining user role", e);
        }
    }

    public String generateToken(String address, String role) {
        return jwtService.generateToken(address, role);
    }
} 