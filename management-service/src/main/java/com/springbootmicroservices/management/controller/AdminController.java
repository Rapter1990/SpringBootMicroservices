package com.springbootmicroservices.management.controller;

import com.springbootmicroservices.management.dto.AdvertisementRequest;
import com.springbootmicroservices.management.model.Advertisement;
import com.springbootmicroservices.management.service.AdminService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin_role")
@RequiredArgsConstructor
public class AdminController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final AdminService adminService;

    @PostMapping("/create/{userId}")
    @CircuitBreaker(name = "management", fallbackMethod = "managementFallback")
    public ResponseEntity<?> createAdvertisement(@RequestBody AdvertisementRequest advertisementRequest, @PathVariable String userId){

        LOGGER.info("AdminController | createAdvertisement is started");

        LOGGER.info("AdminController | createAdvertisement | userId : " + userId);

        adminService.createAdvertisement(advertisementRequest,userId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Advertisement Created");
    }

    @PutMapping("/update/{advertisementId}")
    @CircuitBreaker(name = "management", fallbackMethod = "managementFallback")
    public ResponseEntity<?> updateAdvertisement(@RequestBody AdvertisementRequest advertisementRequest ,@PathVariable String advertisementId){

        LOGGER.info("AdminController | updateAdvertisement is started");

        LOGGER.info("AdminController | updateAdvertisement | advertisementId : " + advertisementId);

        adminService.updateAdvertisement(advertisementRequest,advertisementId);
        return ResponseEntity.status(HttpStatus.OK).body("Advertisement Updated");
    }

    @DeleteMapping("/delete/{advertisementId}")
    @CircuitBreaker(name = "management", fallbackMethod = "managementFallback")
    public ResponseEntity<?> deleteAdvertisement(@PathVariable String advertisementId){

        LOGGER.info("AdminController | deleteAdvertisement is started");

        LOGGER.info("AdminController | deleteAdvertisement | advertisementId : " + advertisementId);

        adminService.deleteAdvertisement(advertisementId);
        return ResponseEntity.status(HttpStatus.OK).body("Advertisement Deleted");
    }

    @GetMapping("/alladvertisements")
    @CircuitBreaker(name = "management", fallbackMethod = "managementFallback")
    public ResponseEntity<List<Advertisement>> getAllAdvertisements(){

        LOGGER.info("AdminController | getAllAdvertisements is started");

        return ResponseEntity.ok(adminService.getAllAdvertisements());
    }

    @GetMapping("/alladvertisements/{advertisementId}")
    @CircuitBreaker(name = "management", fallbackMethod = "managementFallback")
    public ResponseEntity<Advertisement> getAdvertisementById(@PathVariable String advertisementId){

        LOGGER.info("AdminController | getAdvertisementById is started");

        LOGGER.info("AdminController | getAdvertisementById | advertisementId : " + advertisementId);

        return adminService.getAdvertisementById(advertisementId);
    }

    @GetMapping("/advertisement/{advertisementId}/approve")
    @CircuitBreaker(name = "management", fallbackMethod = "managementFallback")
    public ResponseEntity<?> approveAdvertisement(@PathVariable String advertisementId){

        LOGGER.info("AdminController | approveAdvertisement is started");

        LOGGER.info("AdminController | approveAdvertisement | advertisementId : " + advertisementId);

        return adminService.approveAdvertisement(advertisementId);
    }

    @GetMapping("/advertisement/{advertisementId}/reject")
    @CircuitBreaker(name = "management", fallbackMethod = "managementFallback")
    public ResponseEntity<?> rejectAdvertisement(@PathVariable String advertisementId){

        LOGGER.info("AdminController | rejectAdvertisement is started");

        LOGGER.info("AdminController | rejectAdvertisement | advertisementId : " + advertisementId);

        return adminService.rejectAdvertisement(advertisementId);
    }

    public ResponseEntity<?> managementFallback(Exception e) {

        LOGGER.info("AdminController | managementFallback is started");

        LOGGER.info("AdminController | managementFallback | error : " + "CircuitBreaker Works! Error : " + e.getMessage());

        return new ResponseEntity<String>(
                "CircuitBreaker Works! Error : " + e.getMessage(),
                HttpStatus.OK);

    }

}
