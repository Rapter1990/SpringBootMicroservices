package com.springbootmicroservices.userservice.dto;

import lombok.Data;

@Data
public class KeycloakUser {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String role;

}
