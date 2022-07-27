package com.springbootmicroservices.userservice.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class UserContext {
    public static final String CORRELATION_ID = "correlation_id";

    private String correlationId;
}
