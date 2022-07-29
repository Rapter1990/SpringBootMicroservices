package com.springbootmicroservices.advertisement.service.impl;

import com.springbootmicroservices.advertisement.convertor.AdvertisementMapper;
import com.springbootmicroservices.advertisement.dto.AdvertisementRequest;
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

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final AdvertisementRepository advertisementRepository;
    private final MessageService messagingService;

    @Override
    public void createAdvertisement(AdvertisementRequest advertisementRequest, String userId) {

        LOGGER.info("AdminServiceImpl | createAdvertisement is started");

        LOGGER.info("AdminServiceImpl | createAdvertisement | userId : " + userId);

        Advertisement createdAdvertisement = AdvertisementMapper.advertisementRequestToAdvertisement(advertisementRequest);
        createdAdvertisement.setUserId(Long.valueOf(userId));
        createdAdvertisement.setViewCount(1L);
        createdAdvertisement.setState(AdvertisementState.WAITING);

        LOGGER.info("AdminServiceImpl | createAdvertisement | createdAdvertisement state : " + createdAdvertisement.getState().toString());

        advertisementRepository.save(createdAdvertisement);
    }

    @Override
    public void updateAdvertisement(AdvertisementRequest advertisementRequest, String advertisementId) {

        LOGGER.info("AdminServiceImpl | updateAdvertisement is started");

        LOGGER.info("AdminServiceImpl | updateAdvertisement | advertisementId : " + advertisementId);

        Optional<Advertisement> optionalAdvertisement = advertisementRepository.findById(Long.valueOf(advertisementId));

        Advertisement updatedAdvertisement = optionalAdvertisement.get();

        LOGGER.info("AdminServiceImpl | updateAdvertisement | updatedAdvertisement title : " + updatedAdvertisement.getTitle());

        if (updatedAdvertisement.getPrice() != advertisementRequest.getPrice()){
            updatedAdvertisement.setPrice(advertisementRequest.getPrice());
        }
        if (updatedAdvertisement.getTitle() != advertisementRequest.getTitle()){
            updatedAdvertisement.setTitle(advertisementRequest.getTitle());
        }

        updatedAdvertisement.setState(AdvertisementState.WAITING);

        LOGGER.info("AdminServiceImpl | updateAdvertisement | updatedAdvertisement state : " + updatedAdvertisement.getState().toString());

        advertisementRepository.save(updatedAdvertisement);
    }

    @Override
    public void deleteAdvertisement(String advertisementId) {

        LOGGER.info("AdminServiceImpl | deleteAdvertisement is started");

        LOGGER.info("AdminServiceImpl | deleteAdvertisement | advertisementId : " + advertisementId);

        Optional<Advertisement> optionalAdvertisement = advertisementRepository.findById(Long.valueOf(advertisementId));
        Advertisement deletedAdvertisement = optionalAdvertisement.get();

        LOGGER.info("AdminServiceImpl | deleteAdvertisement | deletedAdvertisement title : " + deletedAdvertisement.getTitle());

        advertisementRepository.delete(deletedAdvertisement);
    }

    @Override
    public Advertisement approveAdvertisement(String advertisementId) {

        LOGGER.info("AdminServiceImpl | approveAdvertisement is started");

        LOGGER.info("AdminServiceImpl | approveAdvertisement | advertisementId : " + advertisementId);

        Optional<Advertisement> optionalAdvertise = advertisementRepository.findById(Long.valueOf(advertisementId));

        Advertisement advertisement = optionalAdvertise.get();

        LOGGER.info("AdminServiceImpl | approveAdvertisement | advertisement title : " + advertisement.getTitle());

        advertisement.setState(AdvertisementState.APPROVED);

        // To access Advertisement ID , use saveAndFlush
        advertisementRepository.saveAndFlush(advertisement);

        messagingService.sendMessage(advertisement);

        return advertisement;
    }

    @Override
    public Advertisement rejectAdvertisement(String advertisementId) {

        LOGGER.info("AdminServiceImpl | rejectAdvertisement is started");

        LOGGER.info("AdminServiceImpl | rejectAdvertisement | advertisementId : " + advertisementId);

        Optional<Advertisement> optionalAdvertise = advertisementRepository.findById(Long.valueOf(advertisementId));

        Advertisement advertisement = optionalAdvertise.get();

        LOGGER.info("AdminServiceImpl | approveAdvertisement | advertisement title : " + advertisement.getTitle());

        advertisement.setState(AdvertisementState.REJECTED);

        advertisementRepository.save(advertisement);

        return advertisement;
    }

    @Override
    public List<Advertisement> getAllAdvertisementsForAdmin() {
        LOGGER.info("AdminServiceImpl | getAllAdvertisementsForAdmin is started");

        List<Advertisement> advertiseList = advertisementRepository.findAll();

        LOGGER.info("AdminServiceImpl | getAllAdvertisements | advertiseList size : " + advertiseList.size());

        return advertiseList;
    }

    @Override
    public Advertisement getAdvertisementByIdForAdmin(String advertisementId) {

        LOGGER.info("AdvertisementServiceImpl | getAdvertisementById is started");

        Optional<Advertisement> optionalAdvertisement = advertisementRepository.findById(Long.valueOf(advertisementId));

        Advertisement advertisement = optionalAdvertisement.get();

        LOGGER.info("AdvertisementServiceImpl | getAdvertisementById | advertisement title : " + advertisement.getTitle());

        return advertisement;
    }
}
