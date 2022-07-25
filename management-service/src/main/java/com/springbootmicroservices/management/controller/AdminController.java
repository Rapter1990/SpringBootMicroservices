package com.springbootmicroservices.management.controller;

import com.springbootmicroservices.management.dto.AdvertisementRequest;
import com.springbootmicroservices.management.model.Advertisement;
import com.springbootmicroservices.management.service.AdminService;
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
    public ResponseEntity<?> createAdvertise(@RequestBody AdvertisementRequest advertisementRequest, @PathVariable String userId){
        adminService.createAdvertisement(advertisementRequest,userId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Advertisement Created");
    }
    @PutMapping("/update/{advertisementId}")
    public ResponseEntity<?> updateAdvertise(@RequestBody AdvertisementRequest advertisementRequest ,@PathVariable String advertisementId){
        adminService.updateAdvertisement(advertisementRequest,advertisementId);
        return ResponseEntity.status(HttpStatus.OK).body("Advertisement Updated");
    }

    @DeleteMapping("/delete/{advertisementId}")
    public ResponseEntity<?> deleteAdvertise(@PathVariable String advertisementId){
        adminService.deleteAdvertisement(advertisementId);
        return ResponseEntity.status(HttpStatus.OK).body("Advertisement Deleted");
    }

    @GetMapping("/alladvertisements")
    public ResponseEntity<List<Advertisement>> getAllAdvertisements(){
        return ResponseEntity.ok(adminService.getAllAdvertisements());
    }

    @GetMapping("/advertisement/{advertisementId}")
    public ResponseEntity<Advertisement> getAdvertisementById(@PathVariable String advertisementId){
        return adminService.getAdvertisementById(advertisementId);
    }

    @GetMapping("/advertisement/{advertisementId}/approve")
    public ResponseEntity<?> approveAdvertise(@PathVariable String advertisementId){
        return adminService.approveAdvertisement(advertisementId);
    }

    @GetMapping("/advertisement/{advertisementId}/reject")
    public ResponseEntity<?> rejectAdvertise(@PathVariable String advertisementId){
        return adminService.rejectAdvertisement(advertisementId);
    }

}
