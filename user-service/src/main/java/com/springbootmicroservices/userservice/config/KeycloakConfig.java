package com.springbootmicroservices.userservice.config;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {

    @Bean
    public KeycloakConfigResolver keycloakConfigResolver(){
        return new KeycloakSpringBootConfigResolver();
    }

    @Bean
    public Keycloak keycloak(@Value("http://localhost:8181/auth") String url){
        return Keycloak.getInstance(url,
                "my-realm-client",
                "springbootmicroserviceuser",
                "user123456",
                "spring-boot-microservice-keycloak",
                "o7qglfZy50do87iFJmIy2lA0w4zo53ro");
    }
}
