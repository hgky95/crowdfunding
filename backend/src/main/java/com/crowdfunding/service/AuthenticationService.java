package com.crowdfunding.service;

import com.crowdfunding.generated.RoleManager;
import com.crowdfunding.security.JwtService;
import com.crowdfunding.exception.AuthenticationException;
import com.crowdfunding.exception.CrowdfundingException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import java.security.SignatureException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    
    private final Web3Service web3Service;
    private final JwtService jwtService;

    public String generateNonce(String address) {
        return String.format(
            "Welcome to Crowdfunding!\n" +
            "We require a signature to verify you own this wallet.\n" +
            "Wallet address: %s\n" +
            "Request ID: %s",
            address,
            UUID.randomUUID().toString()
        );
    }

    public String verifySignature(String message, String signature, String address) throws SignatureException {
        if (StringUtils.isAnyEmpty(message, signature, address)) {
            throw new AuthenticationException("Message, signature and address are required", HttpStatus.BAD_REQUEST);
        }

        try {
            // Extract r, s, and v from the signature
            // r is the first 66 characters of the signature: 0x + 32 bytes (64 characters in hex) = 66 characters
            // s is the next 64 characters of the signature: 32 bytes (64 characters in hex) = 64 characters
            // v is the last 2 characters of the signature: 1 byte (2 characters in hex) = 2 characters
            String r = signature.substring(0, 66);
			String s = "0x" + signature.substring(66, 130);
			String v = "0x" + signature.substring(130, 132);

			String publicKey = Sign.signedPrefixedMessageToKey(message.getBytes(), new Sign.SignatureData(
					Numeric.hexStringToByteArray(v)[0], Numeric.hexStringToByteArray(r), Numeric.hexStringToByteArray(s)))
					.toString(16);
            String recoveredAddress = "0x" + Keys.getAddress(publicKey);
            log.info("Recovered address: {}", recoveredAddress);
            
            if (!recoveredAddress.equalsIgnoreCase(address)) {
                throw new AuthenticationException("Invalid signature", HttpStatus.UNAUTHORIZED);
            }
            
            return determineRole(address);
        } catch (Exception e) {
            log.error("Failed to verify signature: ", e);
            throw new AuthenticationException("Failed to verify signature", HttpStatus.UNAUTHORIZED);
        }
    }

    private String determineRole(String address) {
        try {
            RoleManager roleManager = web3Service.loadRoleManager();
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
            throw new CrowdfundingException("Error determining user role: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String generateToken(String address, String role) {
        return jwtService.generateToken(address, role);
    }
} 