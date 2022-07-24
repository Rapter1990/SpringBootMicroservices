package com.springbootmicroservices.userservice.service.impl;

import com.springbootmicroservices.userservice.dto.KeycloakUser;
import com.springbootmicroservices.userservice.dto.LoginRequest;
import com.springbootmicroservices.userservice.service.KeycloakService;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class KeycloakServiceImpl implements KeycloakService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private String realm = "my-realm-client";

    private final Keycloak keycloak;

    public KeycloakServiceImpl(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    @Override
    public AccessTokenResponse loginWithKeycloak(LoginRequest request) {
        Keycloak loginKeycloak = buildKeycloak(request.getUsername(), request.getPassword());

        AccessTokenResponse accessTokenResponse = null;

        try{
            accessTokenResponse = loginKeycloak.tokenManager().getAccessToken();
            return accessTokenResponse;
        }catch (Exception e){
            return null;
        }
    }

    private Keycloak buildKeycloak(String username, String password) {

        return KeycloakBuilder.builder()
                .realm(realm)
                .serverUrl("http://localhost:8181/auth")
                .clientId("spring-boot-microservice-keycloak")
                .clientSecret("o7qglfZy50do87iFJmIy2lA0w4zo53ro")
                .username(username)
                .password(password)
                .build();

    }

    @Override
    public int createUserWithKeycloak(KeycloakUser keycloakUser) {

        LOGGER.info("KeycloakServiceImpl | createUserWithKeycloak is started");

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(keycloakUser.getFirstName());
        userRepresentation.setLastName(keycloakUser.getLastName());
        userRepresentation.setEmail(keycloakUser.getEmail());
        userRepresentation.setUsername(keycloakUser.getUsername());
        HashMap<String, List<String>> clientRoles = new HashMap<>();
        clientRoles.put("spring-boot-microservice-keycloak",Collections.singletonList(keycloakUser.getRole()));
        userRepresentation.setClientRoles(clientRoles);

        userRepresentation.setEnabled(true);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType("password");
        credentialRepresentation.setValue(keycloakUser.getPassword());
        credentialRepresentation.setTemporary(false);


        userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));


        Response response = keycloak.realm(realm).users().create(userRepresentation);

        LOGGER.info("KeycloakServiceImpl | createUserWithKeycloak | response STATUS : " + response.getStatus());
        LOGGER.info("KeycloakServiceImpl | createUserWithKeycloak | response INFO : " + response.getStatusInfo());

        return response.getStatus();
    }
}
