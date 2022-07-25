package com.springbootmicroservices.management.controller;

import com.springbootmicroservices.management.model.Advertisement;
import com.springbootmicroservices.management.model.AdvertisementState;
import com.springbootmicroservices.management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user_role")
@RequiredArgsConstructor
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final UserService userService;

    @GetMapping("/alladvertisements")
    public ResponseEntity<List<Advertisement>> getAllAdvertisements(){

        LOGGER.info("UserController | getAllAdvertisements is started");

        return ResponseEntity.ok(userService.getAllAdvertisements());
    }

    @GetMapping("/advertisement/{advertisementId}")
    public ResponseEntity<?> getAdvertisementById(@PathVariable String advertisementId){

        LOGGER.info("UserController | getAdvertisementById is started");

        LOGGER.info("UserController | getAdvertisementById | advertisementId :  " + advertisementId);

        ResponseEntity<Advertisement> advertisementResponseEntity = userService.getAdvertisementById(advertisementId);

        Advertisement advertisement = advertisementResponseEntity.getBody();

        LOGGER.info("UserController | getAdvertisementById | advertisement state :  " + advertisement.getState());

        if(advertisement.getState() == AdvertisementState.APPROVED){
            return userService.getAdvertisementById(advertisementId);
        }else{
            LOGGER.info("UserController | getAdvertisementById | Advertisement Not Found ");
            return ResponseEntity.ok("Advertisement Not Found");
        }

    }
}
