package com.springbootmicroservices.userservice.service.impl;

import com.springbootmicroservices.userservice.convertor.UserMapper;
import com.springbootmicroservices.userservice.dto.KeycloakUser;
import com.springbootmicroservices.userservice.dto.SignUpRequest;
import com.springbootmicroservices.userservice.entity.User;
import com.springbootmicroservices.userservice.repository.UserRepository;
import com.springbootmicroservices.userservice.service.KeycloakService;
import com.springbootmicroservices.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;
    private final KeycloakService keycloakService;

    @Override
    public String signUpUser(SignUpRequest signUpRequest) {

        LOGGER.info("UserServiceImpl | signUpUser is started");

        KeycloakUser keycloakUser = new KeycloakUser();
        keycloakUser.setFirstName(signUpRequest.getName());
        keycloakUser.setLastName(signUpRequest.getSurname());
        keycloakUser.setEmail(signUpRequest.getEmail());
        keycloakUser.setPassword(signUpRequest.getPassword());
        keycloakUser.setRole(signUpRequest.getRole());
        keycloakUser.setUsername(signUpRequest.getUsername());

        int status = keycloakService.createUserWithKeycloak(keycloakUser);

        if(status == 201){

            LOGGER.info("UserServiceImpl | signUpUser | status : " + status);

            User signUpUser = UserMapper.signUpRequestToUser(signUpRequest);

            signUpUser.setCreatedAt(LocalDateTime.now());

            userRepository.save(signUpUser);

            return "Sign Up completed";
        }

        return "Not Register";
    }
}
