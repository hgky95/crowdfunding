package com.crowdfunding.dto;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String address;
    private String role;
    private String name;
    private String email;
}
