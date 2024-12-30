package com.crowdfunding.service;

import org.springframework.stereotype.Service;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;
import java.math.BigInteger;
import java.util.Arrays;

@Service
public class MessageSigningService {
    
    public String createNonce(String address) {
        return "Welcome to Crowdfunding! Please sign this message to verify your wallet.\n\nNonce: " + 
               System.currentTimeMillis();
    }
    
    public boolean verifySignature(String message, String signature, String address) {
        byte[] messageHash = message.getBytes();
        byte[] signatureBytes = Numeric.hexStringToByteArray(signature);
        
        byte v = signatureBytes[64];
        byte[] r = Arrays.copyOfRange(signatureBytes, 0, 32);
        byte[] s = Arrays.copyOfRange(signatureBytes, 32, 64);
        
        Sign.SignatureData signatureData = new Sign.SignatureData(v, r, s);
        
        BigInteger publicKey = Sign.signedMessageToKey(messageHash, signatureData);
        String recoveredAddress = "0x" + Keys.getAddress(publicKey);
        
        return recoveredAddress.equalsIgnoreCase(address);
    }
} 