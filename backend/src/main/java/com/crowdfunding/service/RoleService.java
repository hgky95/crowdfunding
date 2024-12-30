package com.crowdfunding.service;

import com.crowdfunding.contracts.RoleManager;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RoleService {
    private final Web3j web3j;
    private final RoleManager roleManager;
    private final ConcurrentHashMap<String, UserRole> roleCache;
    
    // Cache roles for quick access
    private enum UserRole {
        STUDENT,
        COMMITTEE,
        DONOR,
        NONE
    }

    public RoleService(Web3j web3j, RoleManager roleManager) {
        this.web3j = web3j;
        this.roleManager = roleManager;
        this.roleCache = new ConcurrentHashMap<>();
    }

    public UserRole getUserRole(String address) {
        // Check cache first
        if (roleCache.containsKey(address)) {
            return roleCache.get(address);
        }

        try {
            if (roleManager.isStudent(address).send()) {
                roleCache.put(address, UserRole.STUDENT);
                return UserRole.STUDENT;
            }
            if (roleManager.isCommittee(address).send()) {
                roleCache.put(address, UserRole.COMMITTEE);
                return UserRole.COMMITTEE;
            }
            if (roleManager.isDonor(address).send()) {
                roleCache.put(address, UserRole.DONOR);
                return UserRole.DONOR;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error checking role: " + e.getMessage());
        }

        return UserRole.NONE;
    }

    public void clearCache(String address) {
        roleCache.remove(address);
    }
} 