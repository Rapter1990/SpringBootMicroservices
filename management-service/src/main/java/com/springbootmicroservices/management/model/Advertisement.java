package com.springbootmicroservices.management.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Advertisement extends IdBasedEntity implements Serializable {

    private Long userId;
    private String title;
    private BigDecimal price;
    private Long viewCount;
    private AdvertisementState state;

}
