package com.springbootmicroservices.report.service.impl;

import com.springbootmicroservices.report.dto.AdvertisementDto;
import com.springbootmicroservices.report.entity.Report;
import com.springbootmicroservices.report.repository.ReportRepository;
import com.springbootmicroservices.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final ReportRepository reportRepository;

    @Override
    public void createReport(AdvertisementDto advertisementDto) {

        LOGGER.info("MessageServiceImpl | createReport is started");

        Report report = new Report();
        report.setAdvertisementId(advertisementDto.getId());
        report.setViewed(advertisementDto.getViewCount());

        report.setMessage("Advertisement Id: " + report.getAdvertisementId()
                + "Advertisement Title : " + advertisementDto.getTitle()
                + " Viewed: " + report.getViewed()
                + " createdAt:" + LocalDateTime.now());

        LOGGER.info("MessageServiceImpl | createReport | Report message : " + report.getMessage());

        reportRepository.save(report);
    }
}
