package com.springbootmicroservices.management.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class UserContext {
    public static final String CORRELATION_ID = "correlation_id";
    public static final String AUTH_TOKEN = "Authorization";

    private String correlationId;
    private String accessToken;
}
