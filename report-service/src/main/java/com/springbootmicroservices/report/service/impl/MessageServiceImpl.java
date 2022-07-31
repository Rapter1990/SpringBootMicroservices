package com.springbootmicroservices.report.service.impl;

import com.springbootmicroservices.report.dto.AdvertisementDto;
import com.springbootmicroservices.report.service.MessageService;
import com.springbootmicroservices.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final ReportService reportService;

    @RabbitListener(queues = "${queue.name}")
    @Override
    public void receiveMessage(@Payload AdvertisementDto advertisementDto) {

        LOGGER.info("MessageServiceImpl | receiveMessage is started");

        LOGGER.info("MessageServiceImpl | receiveMessage | Report is creating");

        reportService.createReport(advertisementDto);
    }
}
