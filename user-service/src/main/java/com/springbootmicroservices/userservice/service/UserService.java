package com.springbootmicroservices.userservice.service;

import com.springbootmicroservices.userservice.dto.SignUpRequest;

public interface UserService {
    public String signUpUser(SignUpRequest signUpRequest);
}
