package com.springbootmicroservices.userservice.controller;

import com.springbootmicroservices.userservice.dto.LoginRequest;
import com.springbootmicroservices.userservice.dto.SignUpRequest;
import com.springbootmicroservices.userservice.entity.User;
import com.springbootmicroservices.userservice.service.KeycloakService;
import com.springbootmicroservices.userservice.service.UserService;
import com.springbootmicroservices.userservice.utils.UserContextHolder;
import lombok.RequiredArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.AccessTokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final UserService userService;
    private final KeycloakService keycloakService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody SignUpRequest signUpRequest){

        LOGGER.info("UserController | signUpUser is started");
        LOGGER.info("UserController | signUpUser | SignUpRequest role : " + signUpRequest.getRole());
        LOGGER.info("UserController | signUpUser | SignUpRequest email : " + signUpRequest.getEmail());
        LOGGER.info("UserController | signUpUser | SignUpRequest name : " + signUpRequest.getName());

        return ResponseEntity.ok(userService.signUpUser(signUpRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AccessTokenResponse> login(@RequestBody LoginRequest request){

        LOGGER.info("UserController | login is started");

        AccessTokenResponse accessTokenResponse =keycloakService.loginWithKeycloak(request);
        if (accessTokenResponse == null){
            LOGGER.info("UserController | login | Http Status Bad Request");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(accessTokenResponse);
        }

        LOGGER.info("UserController | login | Http Status Ok");

        return ResponseEntity.ok(accessTokenResponse);
    }


    @GetMapping("/info")
    public ResponseEntity<String> infoUser(){

        LOGGER.info("UserController | infoUser is started");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        LOGGER.info("UserController | infoUser | auth toString : " + auth.toString());
        LOGGER.info("UserController | infoUser | auth getPrincipal : " + auth.getPrincipal());

        KeycloakPrincipal principal = (KeycloakPrincipal)auth.getPrincipal();
        KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
        AccessToken accessToken = session.getToken();

        String username = accessToken.getPreferredUsername();
        String email = accessToken.getEmail();
        String lastname = accessToken.getFamilyName();
        String firstname = accessToken.getGivenName();
        String realmName = accessToken.getIssuer();
        AccessToken.Access access = accessToken.getRealmAccess();
        Set<String> roles = access.getRoles();

        String role = roles.stream()
                .filter(s -> s.equals("ROLE_USER") || s.equals("ROLE_ADMIN"))
                .findAny()
                .orElse("noElement");

        LOGGER.info("UserController | infoUser | username : " + username);
        LOGGER.info("UserController | infoUser | email : " + email);
        LOGGER.info("UserController | infoUser | lastname : " + lastname);
        LOGGER.info("UserController | infoUser | firstname : " + firstname);
        LOGGER.info("UserController | infoUser | realmName : " + realmName);
        LOGGER.info("UserController | infoUser | firstRole : " + role);

        return ResponseEntity.ok(role);
    }

}
