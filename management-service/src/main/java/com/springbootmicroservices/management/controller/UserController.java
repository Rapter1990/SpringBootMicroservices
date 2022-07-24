package com.springbootmicroservices.management.controller;

import com.springbootmicroservices.management.model.Advertisement;
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
        return ResponseEntity.ok(userService.getAllAdvertisements());
    }

    @GetMapping("/advertisement/{advertiseId}")
    public ResponseEntity<Advertisement> getAdvertisementById(@PathVariable String advertisementId){
        //return ResponseEntity.ok(userService.getAdvertisementById(advertisementId));
        return null;
    }
}
