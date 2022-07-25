package com.springbootmicroservices.advertisement.service.impl;

import com.springbootmicroservices.advertisement.entity.Advertisement;
import com.springbootmicroservices.advertisement.entity.AdvertisementState;
import com.springbootmicroservices.advertisement.repository.AdvertisementRepository;
import com.springbootmicroservices.advertisement.service.AdminService;
import com.springbootmicroservices.advertisement.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final AdvertisementRepository advertiseRepository;
    private final MessageService messagingService;

    @Override
    public Advertisement approveAdvertisement(String advertisementId) {

        LOGGER.info("AdminServiceImpl | approveAdvertisement is started");

        LOGGER.info("AdminServiceImpl | approveAdvertisement | advertisementId : " + advertisementId);

        Optional<Advertisement> optionalAdvertise = advertiseRepository.findById(Long.valueOf(advertisementId));

        Advertisement advertisement = optionalAdvertise.get();

        LOGGER.info("AdminServiceImpl | approveAdvertisement | advertisement title : " + advertisement.getTitle());

        advertisement.setState(AdvertisementState.APPROVED);

        // To access Advertisement ID , use saveAndFlush
        advertiseRepository.saveAndFlush(advertisement);

        messagingService.sendMessage(advertisement);

        return advertisement;
    }

    @Override
    public Advertisement rejectAdvertisement(String advertisementId) {

        LOGGER.info("AdminServiceImpl | rejectAdvertisement is started");

        LOGGER.info("AdminServiceImpl | rejectAdvertisement | advertisementId : " + advertisementId);

        Optional<Advertisement> optionalAdvertise = advertiseRepository.findById(Long.valueOf(advertisementId));

        Advertisement advertisement = optionalAdvertise.get();

        LOGGER.info("AdminServiceImpl | approveAdvertisement | advertisement title : " + advertisement.getTitle());

        advertisement.setState(AdvertisementState.REJECTED);

        advertiseRepository.save(advertisement);

        return advertisement;
    }
}
