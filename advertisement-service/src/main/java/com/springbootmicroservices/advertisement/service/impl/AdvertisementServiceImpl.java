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
    public void createAdvertisement(AdvertisementRequest advertisementRequest, String userId) {

        LOGGER.info("AdvertisementServiceImpl | createAdvertisement is started");

        LOGGER.info("AdvertisementServiceImpl | createAdvertisement | userId : " + userId);

        Advertisement createdAdvertisement = AdvertisementMapper.advertisementRequestToAdvertisement(advertisementRequest);
        createdAdvertisement.setUserId(Long.valueOf(userId));
        createdAdvertisement.setViewCount(1L);
        createdAdvertisement.setState(AdvertisementState.WAITING);

        LOGGER.info("AdvertisementServiceImpl | createAdvertisement | createdAdvertisement state : " + createdAdvertisement.getState().toString());

        advertisementRepository.save(createdAdvertisement);
    }

    @Override
    public void updateAdvertisement(AdvertisementRequest advertisementRequest, String advertisementId) {

        LOGGER.info("AdvertisementServiceImpl | updateAdvertisement is started");

        LOGGER.info("AdvertisementServiceImpl | updateAdvertisement | advertisementId : " + advertisementId);

        Optional<Advertisement> optionalAdvertisement = advertisementRepository.findById(Long.valueOf(advertisementId));

        Advertisement updatedAdvertisement = optionalAdvertisement.get();

        LOGGER.info("AdvertisementServiceImpl | updateAdvertisement | updatedAdvertisement title : " + updatedAdvertisement.getTitle());

        if (updatedAdvertisement.getPrice() != advertisementRequest.getPrice()){
            updatedAdvertisement.setPrice(advertisementRequest.getPrice());
        }
        if (updatedAdvertisement.getTitle() != advertisementRequest.getTitle()){
            updatedAdvertisement.setTitle(advertisementRequest.getTitle());
        }

        updatedAdvertisement.setState(AdvertisementState.WAITING);

        LOGGER.info("AdvertisementServiceImpl | updateAdvertisement | updatedAdvertisement state : " + updatedAdvertisement.getState().toString());

        advertisementRepository.save(updatedAdvertisement);
    }

    @Override
    public void deleteAdvertisement(String advertisementId) {

        LOGGER.info("AdvertisementServiceImpl | deleteAdvertisement is started");

        LOGGER.info("AdvertisementServiceImpl | deleteAdvertisement | advertisementId : " + advertisementId);

        Optional<Advertisement> optionalAdvertisement = advertisementRepository.findById(Long.valueOf(advertisementId));
        Advertisement deletedAdvertisement = optionalAdvertisement.get();

        LOGGER.info("AdvertisementServiceImpl | deleteAdvertisement | deletedAdvertisement title : " + deletedAdvertisement.getTitle());

        advertisementRepository.delete(deletedAdvertisement);
    }

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
    public List<Advertisement> getAllAdvertisementsForAdmin() {

        LOGGER.info("AdvertisementServiceImpl | getAllAdvertisementsForAdmin is started");

        List<Advertisement> advertiseList = advertisementRepository.findAll();

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
