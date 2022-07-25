package com.springbootmicroservices.report.service.impl;

import com.springbootmicroservices.report.dto.AdvertisementDto;
import com.springbootmicroservices.report.entity.Report;
import com.springbootmicroservices.report.service.MessageService;
import com.springbootmicroservices.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final ReportService reportService;

    @RabbitListener(queues = "${queue.name}")
    @Override
    public void receiveMessage(AdvertisementDto advertisementDto) {

        reportService.createReport(advertisementDto);
    }
}
