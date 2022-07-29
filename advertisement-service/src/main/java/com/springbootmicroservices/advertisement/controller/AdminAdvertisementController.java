package com.springbootmicroservices.advertisement.controller;

import com.springbootmicroservices.advertisement.dto.AdvertisementRequest;
import com.springbootmicroservices.advertisement.entity.Advertisement;
import com.springbootmicroservices.advertisement.service.AdminService;
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

    //private final AdvertisementService advertiseService;
    private final AdminService adminService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createAdvertisement(@RequestBody AdvertisementRequest advertisementRequest, @PathVariable String userId){

        LOGGER.info("AdminAdvertisementController | createAdvertisement is started");

        adminService.createAdvertisement(advertisementRequest,userId);

        LOGGER.info("AdminAdvertisementController | createAdvertisement | Advertisement Created");

        return ResponseEntity.status(HttpStatus.CREATED).body("Advertisement Created");
    }

    @PutMapping("/update/{advertisementId}")
    public ResponseEntity<?> updateAdvertisement(@RequestBody AdvertisementRequest advertisementRequest ,@PathVariable String advertisementId){

        LOGGER.info("AdminAdvertisementController | updateAdvertisement is started");

        LOGGER.info("AdminAdvertisementController | updateAdvertisement | advertisementId : " + advertisementId);

        adminService.updateAdvertisement(advertisementRequest,advertisementId);

        LOGGER.info("AdminAdvertisementController | createAdvertisement | Advertisement Created");

        return ResponseEntity.status(HttpStatus.OK).body("Advertisement Updated");
    }

    @DeleteMapping("/delete/{advertisementId}")
    public ResponseEntity<?> deleteAdvertise(@PathVariable String advertisementId){

        LOGGER.info("AdminAdvertisementController | updateAdvertisement is started");

        LOGGER.info("AdminAdvertisementController | updateAdvertisement | advertisementId : " + advertisementId);

        adminService.deleteAdvertisement(advertisementId);

        LOGGER.info("AdminAdvertisementController | createAdvertisement | Advertisement Deleted");

        return ResponseEntity.status(HttpStatus.OK).body("Advertisement Deleted");
    }

    @GetMapping("/alladvertisements")
    public ResponseEntity<List<Advertisement>> getAllAdvertisements(){

        LOGGER.info("AdminAdvertisementController | getAllAdvertisements is started");

        return ResponseEntity.ok(adminService.getAllAdvertisementsForAdmin());
    }

    @GetMapping("/advertisement/{advertisementId}")
    public ResponseEntity<Advertisement> getAdvertisementById(@PathVariable String advertisementId){

        LOGGER.info("AdminAdvertisementController | getAdvertisementById is started");

        LOGGER.info("AdminAdvertisementController | getAdvertisementById | getAdvertisementById " + advertisementId);

        return ResponseEntity.ok(adminService.getAdvertisementByIdForAdmin(advertisementId));
    }

    @GetMapping("/advertisement/{advertisementId}/approve")
    public ResponseEntity<?> approveAdvertisement(@PathVariable String advertisementId){

        LOGGER.info("AdminAdvertisementController | approveAdvertisement is started");

        LOGGER.info("AdminAdvertisementController | approveAdvertisement | getAdvertisementById " + advertisementId);

        Advertisement advertisement = null;
        try{
            advertisement = adminService.approveAdvertisement(advertisementId);
        }catch (Exception e){
            LOGGER.info("AdminAdvertisementController | approveAdvertisement | error " + e.getMessage());
        }

        LOGGER.info("AdminAdvertisementController | approveAdvertisement | Approved ");

        return ResponseEntity.ok("Approved");
    }

    @GetMapping("/advertisement/{advertisementId}/reject")
    public ResponseEntity<?> rejectAdvertisement(@PathVariable String advertisementId){

        LOGGER.info("AdminAdvertisementController | rejectAdvertisement is started");

        LOGGER.info("AdminAdvertisementController | rejectAdvertisement | getAdvertisementById " + advertisementId);

        Advertisement advertisement = null;
        try{
            advertisement = adminService.rejectAdvertisement(advertisementId);
        }catch (Exception e){
            LOGGER.info("AdminAdvertisementController | rejectAdvertisement | error " + e.getMessage());
        }

        LOGGER.info("AdminAdvertisementController | approveAdvertisement | Rejected ");

        return ResponseEntity.ok("Rejected");
    }
}
