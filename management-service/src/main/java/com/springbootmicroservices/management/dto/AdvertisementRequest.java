package com.springbootmicroservices.management.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AdvertisementRequest {

    private String title;
    private BigDecimal price;
}
