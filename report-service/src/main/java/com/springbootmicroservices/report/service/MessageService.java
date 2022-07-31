package com.springbootmicroservices.report.service;

import com.springbootmicroservices.advertisement.dto.AdvertisementDto;

public interface MessageService {
    void receiveMessage(AdvertisementDto advertisementDto);
}
