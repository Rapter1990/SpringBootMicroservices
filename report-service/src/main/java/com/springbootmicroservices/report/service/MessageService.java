package com.springbootmicroservices.report.service;

import com.springbootmicroservices.report.dto.AdvertisementDto;

public interface MessageService {
    void receiveMessage(AdvertisementDto advertisementDto);
}
