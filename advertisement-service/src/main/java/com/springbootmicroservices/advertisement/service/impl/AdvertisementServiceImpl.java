package com.springbootmicroservices.advertisement.service.impl;

import com.springbootmicroservices.advertisement.convertor.AdvertisementMapper;
import com.springbootmicroservices.advertisement.dto.AdvertisementRequest;
import com.springbootmicroservices.advertisement.entity.Advertisement;
import com.springbootmicroservices.advertisement.entity.AdvertisementState;
import com.springbootmicroservices.advertisement.repository.AdvertisementRepository;
import com.springbootmicroservices.advertisement.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final AdvertisementRepository advertisementRepository;


    @Override
    public List<Advertisement> getAllAdvertisements() {

        LOGGER.info("AdvertisementServiceImpl | getAllAdvertisements is started");

        List<Advertisement> advertiseList = advertisementRepository.findAll();

        LOGGER.info("AdvertisementServiceImpl | getAllAdvertisements | advertiseList size : " + advertiseList.size());

        advertiseList.stream().filter(advertisement-> advertisement.getState() == AdvertisementState.APPROVED).forEach(
                advertisement -> advertisement.setViewCount(advertisement.getViewCount()+1));

        LOGGER.info("AdvertisementServiceImpl | getAllAdvertisements | advertiseList size : " + advertiseList.size());

        return advertiseList;
    }

    @Override
    public Advertisement getAdvertisementById(String advertisementId) {

        LOGGER.info("AdvertisementServiceImpl | getAdvertisementById is started");

        Optional<Advertisement> optionalAdvertisement = advertisementRepository.findById(Long.valueOf(advertisementId));

        Advertisement advertisement = optionalAdvertisement.get();

        LOGGER.info("AdvertisementServiceImpl | getAdvertisementById | advertisement title : " + advertisement.getTitle());

        advertisement.setViewCount(advertisement.getViewCount()+1);

        return advertisement;
    }
}
