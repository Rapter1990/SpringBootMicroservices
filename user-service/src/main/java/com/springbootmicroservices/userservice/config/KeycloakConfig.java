package com.springbootmicroservices.userservice.config;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    public final static String serverUrl = "http://localhost:8181";
    public final static String realm = "master";
    public final static String clientId = "spring-boot-microservice-keycloak";
    public final static String clientSecret = "hLfIzFvPpYhKAqmmM5tfqkrnSHEJdpAu";
    final static String userName = "admin";
    final static String password = "admin";

    @Bean
    public KeycloakConfigResolver keycloakConfigResolver(){
        return new KeycloakSpringBootConfigResolver();
    }

    @Bean
    public Keycloak keycloak(){
          return Keycloak.getInstance(serverUrl,
                realm,
                userName,
                password,
                clientId,
                clientSecret);
    }
}
