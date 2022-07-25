package com.springbootmicroservices.report.service.impl;

import com.springbootmicroservices.report.dto.AdvertisementDto;
import com.springbootmicroservices.report.entity.Report;
import com.springbootmicroservices.report.repository.ReportRepository;
import com.springbootmicroservices.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;


    @Override
    public void createReport(AdvertisementDto advertisementDto) {

        Report report = new Report();
        report.setAdvertisementId(advertisementDto.getId());
        report.setViewed(advertisementDto.getViewCount());

        report.setMessage("Advertisement Id: " + report.getAdvertisementId()
                + " Viewed: " + report.getViewed()
                + " createdAt:" + LocalDateTime.now());

        reportRepository.save(report);
    }
}
