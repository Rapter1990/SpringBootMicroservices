package com.springbootmicroservices.advertisement.dto;

import lombok.Data;

@Data
public class AdvertisementDto {

    private Long id;
    private String title;
    private Long viewCount;
}
