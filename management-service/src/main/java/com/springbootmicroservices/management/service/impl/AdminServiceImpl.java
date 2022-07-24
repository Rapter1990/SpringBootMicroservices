package com.springbootmicroservices.management.service.impl;

import com.springbootmicroservices.management.dto.AdvertisementRequest;
import com.springbootmicroservices.management.model.Advertisement;
import com.springbootmicroservices.management.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private static final String BASE_URL = "http://ADVERTISEMENT-SERVICE:9001/api/v1/admin_role";
    private final RestTemplate restTemplate;

    @Override
    public ResponseEntity<?> createAdvertisement(AdvertisementRequest advertisementRequest, String userId) {
        return restTemplate.postForEntity(
                BASE_URL + "/create/{userId}",
                advertisementRequest,
                String.class,
                userId
        );
    }

    @Override
    public ResponseEntity<?> updateAdvertisement(AdvertisementRequest advertisementRequest, String advertisementId) {

        /*return restTemplate.exchange(
                BASE_URL + "/update/"+ advertisementId,
                HttpMethod.DELETE,
                advertisementRequest,
                String.class).getBody();*/

        return null;
    }

    @Override
    public ResponseEntity<?> deleteAdvertisement(String advertisementId) {
        /*return restTemplate.exchange(
                BASE_URL + "/delete/{advertisementId}", HttpMethod.DELETE, entity, String.class).getBody();*/

        return null;
    }

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
    public ResponseEntity<Advertisement> getAdvertisementById(String advertisementId) {
        /*return restTemplate.getForEntity(
                BASE_URL + "/advertisement/{advertisementId}",
                String.class,
                advertisementId
        );*/
        return null;
    }

    @Override
    public ResponseEntity<?> approveAdvertisement(String advertisementId) {
        return restTemplate.getForEntity(
                BASE_URL + "/advertisement/{advertisementId}/approve",
                String.class,
                advertisementId
        );
    }

    @Override
    public ResponseEntity<?> rejectAdvertisement(String advertisementId) {
        return restTemplate.getForEntity(
                BASE_URL + "/advertisement/{advertisementId}/reject",
                String.class,
                advertisementId
        );
    }
}
