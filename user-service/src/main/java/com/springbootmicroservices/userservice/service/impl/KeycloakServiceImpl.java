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

    private String realm = "Client";

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
                .serverUrl("http://localhost:8080/auth")
                .clientId("spring-boot-microservice-keycloak")
                .username(username)
                .password(password)
                .build();

    }

    @Override
    public int createUserWithKeycloak(KeycloakUser keycloakUser) {

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(keycloakUser.getFirstName());
        userRepresentation.setLastName(keycloakUser.getLastName());
        userRepresentation.setEmail(keycloakUser.getEmail());
        userRepresentation.setUsername(keycloakUser.getEmail());
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

        LOGGER.info("KeycloakServiceImpl | createUserWithKeycloak | response status : " + response.getStatus());

        return response.getStatus();
    }
}
