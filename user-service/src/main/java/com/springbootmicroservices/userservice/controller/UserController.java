package com.springbootmicroservices.userservice.controller;

import com.springbootmicroservices.userservice.dto.LoginRequest;
import com.springbootmicroservices.userservice.entity.User;
import com.springbootmicroservices.userservice.service.KeycloakService;
import com.springbootmicroservices.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.AccessTokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final UserService userService;
    private final KeycloakService keycloakService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody User user){

        LOGGER.info("UserController | signUpUser is started");
        LOGGER.info("UserController | getByCityId | user : " + user.getRole());
        LOGGER.info("UserController | getByCityId | user : " + user.getEmail());
        LOGGER.info("UserController | getByCityId | user : " + user.getName());

        return ResponseEntity.ok(userService.signUpUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AccessTokenResponse> login(@RequestBody LoginRequest request){
        AccessTokenResponse accessTokenResponse =keycloakService.loginWithKeycloak(request);
        if (accessTokenResponse == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(accessTokenResponse);
        }
        return ResponseEntity.ok(accessTokenResponse);
    }


    @PostMapping("/info")
    public ResponseEntity<?> infoUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        KeycloakPrincipal principal = (KeycloakPrincipal)auth.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        AccessToken accessToken = session.getToken();
        String username = accessToken.getPreferredUsername();
        String emailID = accessToken.getEmail();
        String lastname = accessToken.getFamilyName();
        String firstname = accessToken.getGivenName();
        String realmName = accessToken.getIssuer();
        AccessToken.Access access = accessToken.getRealmAccess();
        Set<String> roles = access.getRoles();
        String firstRole = roles.stream().findFirst().get();
        return ResponseEntity.ok(firstRole);
    }

}
