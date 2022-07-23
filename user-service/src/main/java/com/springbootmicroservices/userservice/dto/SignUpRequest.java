package com.springbootmicroservices.userservice.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String role;
}
