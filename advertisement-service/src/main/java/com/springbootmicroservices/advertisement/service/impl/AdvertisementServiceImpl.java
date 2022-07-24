package com.springbootmicroservices.advertisement.service.impl;

import com.springbootmicroservices.advertisement.convertor.AdvertisementMapper;
import com.springbootmicroservices.advertisement.dto.AdvertisementRequest;
import com.springbootmicroservices.advertisement.entity.Advertisement;
import com.springbootmicroservices.advertisement.entity.AdvertisementState;
import com.springbootmicroservices.advertisement.repository.AdvertisementRepository;
import com.springbootmicroservices.advertisement.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    @Override
    public void createAdvertisement(AdvertisementRequest advertisementRequest, String userId) {

        Advertisement createdAdvertisement = AdvertisementMapper.advertisementRequestToAdvertisement(advertisementRequest);
        createdAdvertisement.setUserId(Long.valueOf(userId));
        createdAdvertisement.setViewCount(1L);
        createdAdvertisement.setState(AdvertisementState.WAITING);
        advertisementRepository.save(createdAdvertisement);
    }

    @Override
    public void updateAdvertisement(AdvertisementRequest advertisementRequest, String advertisementId) {

        Optional<Advertisement> optionalAdvertisement = advertisementRepository.findById(Long.valueOf(advertisementId));

        Advertisement updatedAdvertisement = optionalAdvertisement.get();

        if (updatedAdvertisement.getPrice() != advertisementRequest.getPrice()){
            updatedAdvertisement.setPrice(advertisementRequest.getPrice());
        }
        if (updatedAdvertisement.getTitle() != advertisementRequest.getTitle()){
            updatedAdvertisement.setTitle(advertisementRequest.getTitle());
        }

        updatedAdvertisement.setState(AdvertisementState.WAITING);

        advertisementRepository.save(updatedAdvertisement);
    }

    @Override
    public void deleteAdvertisement(String advertisementId) {

        Optional<Advertisement> optionalAdvertisement = advertisementRepository.findById(Long.valueOf(advertisementId));
        Advertisement deletedAdvertisement = optionalAdvertisement.get();
        advertisementRepository.delete(deletedAdvertisement);
    }

    @Override
    public List<Advertisement> getAllAdvertisements() {
        List<Advertisement> advertiseList = advertisementRepository.findAll();
        advertiseList.stream().forEach(
                advertisement -> advertisement.setViewCount(advertisement.getViewCount()+1));
        return advertiseList;
    }

    @Override
    public Advertisement getAdvertisementById(String advertisementId) {
        Optional<Advertisement> optionalAdvertisement = advertisementRepository.findById(Long.valueOf(advertisementId));

        Advertisement advertisement = optionalAdvertisement.get();

        advertisement.setViewCount(advertisement.getViewCount()+1);

        return advertisement;
    }
}
