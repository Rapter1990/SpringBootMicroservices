package com.springbootmicroservices.management.service;

import com.springbootmicroservices.management.model.Advertisement;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    List<Advertisement> getAllAdvertisements();

    ResponseEntity<Advertisement> getAdvertisementById(String advertisementId);
}
