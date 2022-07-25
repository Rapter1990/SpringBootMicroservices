package com.springbootmicroservices.advertisement.controller;

import com.springbootmicroservices.advertisement.dto.AdvertisementRequest;
import com.springbootmicroservices.advertisement.entity.Advertisement;
import com.springbootmicroservices.advertisement.service.AdminService;
import com.springbootmicroservices.advertisement.service.AdvertisementService;
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
public class AdminAdvertisementController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final AdvertisementService advertiseService;
    private final AdminService adminService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createAdvertise(@RequestBody AdvertisementRequest advertisementRequest, @PathVariable String userId){
        advertiseService.createAdvertisement(advertisementRequest,userId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Advertisement Created");
    }

    @PutMapping("/update/{advertisementId}")
    public ResponseEntity<?> updateAdvertise(@RequestBody AdvertisementRequest advertisementRequest ,@PathVariable String advertisementId){
        advertiseService.updateAdvertisement(advertisementRequest,advertisementId);
        return ResponseEntity.status(HttpStatus.OK).body("Advertisement Updated");
    }

    @DeleteMapping("/delete/{advertisementId}")
    public ResponseEntity<?> deleteAdvertise(@PathVariable String advertisementId){
        advertiseService.deleteAdvertisement(advertisementId);
        return ResponseEntity.status(HttpStatus.OK).body("Advertisement Deleted");
    }

    @GetMapping("/alladvertisements")
    public ResponseEntity<List<Advertisement>> getAllAdvertisements(){
        return ResponseEntity.ok(advertiseService.getAllAdvertisementsForAdmin());
    }

    @GetMapping("/advertisement/{advertisementId}")
    public ResponseEntity<Advertisement> getAdvertisementById(@PathVariable String advertisementId){
        return ResponseEntity.ok(advertiseService.getAdvertisementById(advertisementId));
    }

    @GetMapping("/advertisement/{advertisementId}/approve")
    public ResponseEntity<?> approveAdvertise(@PathVariable String advertisementId){
        Advertisement advertisement = null;
        try{
            advertisement = adminService.approveAdvertisement(advertisementId);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok("Approved");
    }

    @GetMapping("/advertisement/{advertisementId}/reject")
    public ResponseEntity<?> rejectAdvertise(@PathVariable String advertisementId){
        Advertisement advertisement = null;
        try{
            advertisement = adminService.rejectAdvertisement(advertisementId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok("Rejected");
    }
}
