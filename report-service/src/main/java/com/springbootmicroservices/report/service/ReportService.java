package com.springbootmicroservices.report.service;

import com.springbootmicroservices.advertisement.dto.AdvertisementDto;

public interface ReportService {
    void createReport(AdvertisementDto advertisementDto);
}
