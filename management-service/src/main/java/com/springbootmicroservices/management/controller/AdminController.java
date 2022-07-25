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
    public ResponseEntity<?> createAdvertise(@RequestBody AdvertisementRequest advertisementRequest, @PathVariable String userId){
        adminService.createAdvertisement(advertisementRequest,userId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Advertisement Created");
    }

    @PutMapping("/update/{advertisementId}")
    @CircuitBreaker(name = "management", fallbackMethod = "cardFallback")
    public ResponseEntity<?> updateAdvertise(@RequestBody AdvertisementRequest advertisementRequest ,@PathVariable String advertisementId){
        adminService.updateAdvertisement(advertisementRequest,advertisementId);
        return ResponseEntity.status(HttpStatus.OK).body("Advertisement Updated");
    }

    @DeleteMapping("/delete/{advertisementId}")
    @CircuitBreaker(name = "management", fallbackMethod = "cardFallback")
    public ResponseEntity<?> deleteAdvertise(@PathVariable String advertisementId){
        adminService.deleteAdvertisement(advertisementId);
        return ResponseEntity.status(HttpStatus.OK).body("Advertisement Deleted");
    }

    @GetMapping("/alladvertisements")
    @CircuitBreaker(name = "management", fallbackMethod = "cardFallback")
    public ResponseEntity<List<Advertisement>> getAllAdvertisements(){
        return ResponseEntity.ok(adminService.getAllAdvertisements());
    }

    @GetMapping("/advertisement/{advertisementId}")
    @CircuitBreaker(name = "management", fallbackMethod = "cardFallback")
    public ResponseEntity<Advertisement> getAdvertisementById(@PathVariable String advertisementId){
        return adminService.getAdvertisementById(advertisementId);
    }

    @GetMapping("/advertisement/{advertisementId}/approve")
    @CircuitBreaker(name = "management", fallbackMethod = "cardFallback")
    public ResponseEntity<?> approveAdvertise(@PathVariable String advertisementId){
        return adminService.approveAdvertisement(advertisementId);
    }

    @GetMapping("/advertisement/{advertisementId}/reject")
    @CircuitBreaker(name = "management", fallbackMethod = "cardFallback")
    public ResponseEntity<?> rejectAdvertise(@PathVariable String advertisementId){
        return adminService.rejectAdvertisement(advertisementId);
    }

    public ResponseEntity<?> managementFallback(Exception e) {

        return new ResponseEntity<String>(
                "CircuitBreaker Works! Error : " + e.getMessage(),
                HttpStatus.OK);

    }

}
