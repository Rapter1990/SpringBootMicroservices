package com.springbootmicroservices.report.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdvertisementDto implements Serializable {

    private Long id;
    private String title;
    private Long viewCount;
}
