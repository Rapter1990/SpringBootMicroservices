package com.springbootmicroservices.advertisement.service;

import com.springbootmicroservices.advertisement.dto.AdvertisementRequest;
import com.springbootmicroservices.advertisement.entity.Advertisement;

import java.util.List;

public interface AdvertisementService {

    List<Advertisement> getAllAdvertisements();

    Advertisement getAdvertisementById(String advertisementId);

}
