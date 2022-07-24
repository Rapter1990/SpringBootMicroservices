package com.springbootmicroservices.management.controller;

import com.springbootmicroservices.management.dto.AdvertisementRequest;
import com.springbootmicroservices.management.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin_role")
@RequiredArgsConstructor
public class AdminController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final AdminService adminService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createAdvertise(@RequestBody AdvertisementRequest advertisementRequest, @PathVariable String userId){
        adminService.createAdvertisement(advertisementRequest,userId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Advertisement Created");
    }

}
