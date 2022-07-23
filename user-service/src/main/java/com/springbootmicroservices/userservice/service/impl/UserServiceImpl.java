package com.springbootmicroservices.userservice.service.impl;

import com.springbootmicroservices.userservice.dto.KeycloakUser;
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
    public String signUpUser(User user) {

        LOGGER.info("UserServiceImpl | signUpUser is started");

        KeycloakUser keycloakUser = new KeycloakUser();
        keycloakUser.setFirstName(user.getName());
        keycloakUser.setLastName(user.getSurname());
        keycloakUser.setEmail(user.getEmail());
        keycloakUser.setPassword(user.getPassword());
        keycloakUser.setRole(user.getRole());

        int status = keycloakService.createUserWithKeycloak(keycloakUser);

        LOGGER.info("UserServiceImpl | signUpUser | status : " + status);

        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        return "Sign In";
    }
}
