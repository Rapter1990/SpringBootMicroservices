package com.springbootmicroservices.report.service;

import com.springbootmicroservices.report.dto.AdvertisementDto;

public interface ReportService {
    void createReport(AdvertisementDto advertisementDto);
}
