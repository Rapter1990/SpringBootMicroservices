package com.springbootmicroservices.management.service.impl;

import com.springbootmicroservices.management.model.Advertisement;
import com.springbootmicroservices.management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String BASE_URL = "http://ADVERTISEMENT-SERVICE:9001/api/v1/user_role";
    private final RestTemplate restTemplate;

    @Override
    public List<Advertisement> getAllAdvertisements() {
        ResponseEntity<Advertisement[]> restExchange =
                restTemplate.getForEntity(
                        BASE_URL,
                        Advertisement[].class
                );

        return Arrays.asList(restExchange.getBody());
    }

    @Override
    public ResponseEntity<Advertisement>  getAdvertisementById(String advertisementId) {
        /*return restTemplate.getForEntity(
                BASE_URL + "/advertisement/{advertisementId}",
                String.class,
                advertisementId
        );*/
        return null;
    }
}
