package com.springbootmicroservices.advertisement.service.impl;

import com.springbootmicroservices.advertisement.entity.Advertisement;
import com.springbootmicroservices.advertisement.entity.AdvertisementState;
import com.springbootmicroservices.advertisement.repository.AdvertisementRepository;
import com.springbootmicroservices.advertisement.service.AdminService;
import com.springbootmicroservices.advertisement.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdvertisementRepository advertiseRepository;
    private final MessageService messagingService;

    @Override
    public Advertisement approveAdvertisement(String advertisementId) {
        Optional<Advertisement> optionalAdvertise = advertiseRepository.findById(Long.valueOf(advertisementId));

        Advertisement advertisement = optionalAdvertise.get();
        advertisement.setState(AdvertisementState.APPROVED);

        // To access Advertisement ID , use saveAndFlush
        advertiseRepository.saveAndFlush(advertisement);

        messagingService.sendMessage(advertisement);

        return advertisement;
    }

    @Override
    public Advertisement rejectAdvertisement(String advertisementId) {

        Optional<Advertisement> optionalAdvertise = advertiseRepository.findById(Long.valueOf(advertisementId));

        Advertisement advertisement = optionalAdvertise.get();
        advertisement.setState(AdvertisementState.REJECTED);

        advertiseRepository.save(advertisement);

        return advertisement;
    }
}
