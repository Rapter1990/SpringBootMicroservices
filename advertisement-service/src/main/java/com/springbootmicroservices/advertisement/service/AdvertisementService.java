package com.springbootmicroservices.advertisement.service;

import com.springbootmicroservices.advertisement.dto.AdvertisementRequest;
import com.springbootmicroservices.advertisement.entity.Advertisement;

import java.util.List;

public interface AdvertisementService {

    void createAdvertisement(AdvertisementRequest advertisementRequest, String userId);

    void updateAdvertisement(AdvertisementRequest advertisementRequest, String advertisementId);

    void deleteAdvertisement(String advertisementId);

    List<Advertisement> getAllAdvertisements();

    List<Advertisement> getAllAdvertisementsForAdmin();

    Advertisement getAdvertisementById(String advertisementId);
}
