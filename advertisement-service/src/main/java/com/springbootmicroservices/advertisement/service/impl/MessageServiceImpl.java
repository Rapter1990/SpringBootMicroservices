package com.springbootmicroservices.advertisement.service.impl;

import com.springbootmicroservices.advertisement.dto.AdvertisementDto;
import com.springbootmicroservices.advertisement.entity.Advertisement;
import com.springbootmicroservices.advertisement.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;

    @Override
    public void sendMessage(Advertisement advertisement) {
        AdvertisementDto advertisementDto = new AdvertisementDto();
        advertisementDto.setId(advertisement.getId());
        advertisementDto.setTitle(advertisement.getTitle());
        advertisementDto.setViewCount(advertisement.getViewCount());

        rabbitTemplate.convertAndSend(queue.getName(),advertisementDto);
    }
}
