package com.springbootmicroservices.advertisement.service;

import com.springbootmicroservices.advertisement.dto.AdvertisementRequest;
import com.springbootmicroservices.advertisement.entity.Advertisement;

import java.util.List;

public interface AdminService {

    void createAdvertisement(AdvertisementRequest advertisementRequest, String userId);

    void updateAdvertisement(AdvertisementRequest advertisementRequest, String advertisementId);

    void deleteAdvertisement(String advertisementId);

    Advertisement approveAdvertisement(String advertisementId);

    Advertisement rejectAdvertisement(String advertisementId);

    List<Advertisement> getAllAdvertisementsForAdmin();

    Advertisement getAdvertisementByIdForAdmin(String advertisementId);

}
