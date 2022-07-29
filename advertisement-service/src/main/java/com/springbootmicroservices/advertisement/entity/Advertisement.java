package com.springbootmicroservices.advertisement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "Advertisement")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Advertisement extends IdBasedEntity implements Serializable {

    private Long userId;
    private String title;
    private BigDecimal price;
    private Long viewCount;

    @Enumerated(EnumType.STRING)
    private AdvertisementState state;

}
