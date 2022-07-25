package com.springbootmicroservices.management.service.impl;

import com.springbootmicroservices.management.dto.AdvertisementRequest;
import com.springbootmicroservices.management.model.Advertisement;
import com.springbootmicroservices.management.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private static final String BASE_URL = "http://ADVERTISEMENT-SERVICE:9001/api/v1/admin_role";
    private static final String USER_BASE_URL = "http://USER-SERVICE:9000/api/v1/users";
    private final RestTemplate restTemplate;

    @Override
    public ResponseEntity<?> createAdvertisement(AdvertisementRequest advertisementRequest, String userId) {

        String result = getRoleInfo();

        if(result.equals("ROLE_ADMIN")){
            return restTemplate.postForEntity(
                    BASE_URL + "/create/{userId}",
                    advertisementRequest,
                    String.class,
                    userId
            );
        }

        return null;
    }

    @Override
    public ResponseEntity<?> updateAdvertisement(AdvertisementRequest advertisementRequest, String advertisementId) {

        String result = getRoleInfo();

        if(result.equals("ROLE_ADMIN")){
            /*Map< String, String > params = new HashMap< >();
            params.put("id", advertisementId);
            restTemplate.put(BASE_URL + "/update/"+ advertisementId, advertisementRequest, params);
            return new ResponseEntity("Advertisement Updated with id " + advertisementId, HttpStatus.OK);*/

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity < AdvertisementRequest > entity = new HttpEntity<>(advertisementRequest, httpHeaders);
            return restTemplate.exchange(BASE_URL + "/update/"+ advertisementId, HttpMethod.PUT, entity, String.class);
        }

        return null;
    }

    @Override
    public ResponseEntity<?> deleteAdvertisement(String advertisementId) {

        String result = getRoleInfo();

        if(result.equals("ROLE_ADMIN")){
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity < String > entity = new HttpEntity < > (httpHeaders);
            return restTemplate.exchange(BASE_URL + "/delete/" + advertisementId, HttpMethod.DELETE, entity, String.class);
        }

        return null;
    }

    @Override
    public List<Advertisement> getAllAdvertisements() {

        String result = getRoleInfo();

        if(result.equals("ROLE_ADMIN")){
            ResponseEntity<Advertisement[]> restExchange =
                    restTemplate.getForEntity(
                            BASE_URL,
                            Advertisement[].class
                    );

            return Arrays.asList(restExchange.getBody());
        }

       return null;
    }

    @Override
    public ResponseEntity<Advertisement> getAdvertisementById(String advertisementId) {

        String result = getRoleInfo();

        if(result.equals("ROLE_USER")){
            return restTemplate.getForEntity(
                    BASE_URL + "/advertisement/{advertisementId}",
                    Advertisement.class,
                    advertisementId
            );
        }

        return null;
    }

    @Override
    public ResponseEntity<?> approveAdvertisement(String advertisementId) {

        String result = getRoleInfo();

        if(result.equals("ROLE_ADMIN")){
            return restTemplate.getForEntity(
                    BASE_URL + "/advertisement/{advertisementId}/approve",
                    String.class,
                    advertisementId
            );
        }

        return null;
    }

    @Override
    public ResponseEntity<?> rejectAdvertisement(String advertisementId) {

        String result = getRoleInfo();

        if(result.equals("ROLE_ADMIN")){
            return restTemplate.getForEntity(
                    BASE_URL + "/advertisement/{advertisementId}/reject",
                    String.class,
                    advertisementId
            );
        }

        return null;
    }

    private String getRoleInfo(){
        String result = this.restTemplate.getForObject(USER_BASE_URL + "/info",String.class);
        return result;
    }
}
