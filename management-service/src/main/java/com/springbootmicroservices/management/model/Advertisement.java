package com.springbootmicroservices.management.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Advertisement implements Serializable {

    private Long id;
    private Long userId;
    private String title;
    private BigDecimal price;
    private Long viewCount;
    private AdvertisementState state;

}
