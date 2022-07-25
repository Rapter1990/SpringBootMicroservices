package com.springbootmicroservices.management.service;

import com.springbootmicroservices.management.dto.AdvertisementRequest;
import com.springbootmicroservices.management.model.Advertisement;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdminService {

    ResponseEntity<?> createAdvertisement(AdvertisementRequest advertisementRequest, String userId);

    ResponseEntity<?> updateAdvertisement(AdvertisementRequest advertisementRequest, String advertisementId);

    ResponseEntity<?> deleteAdvertisement(String advertisementId);

    List<Advertisement> getAllAdvertisements();

    ResponseEntity<Advertisement> getAdvertisementById(String advertisementId);

    ResponseEntity<?> approveAdvertisement(String advertisementId);

    ResponseEntity<?> rejectAdvertisement(String advertisementId);
}
