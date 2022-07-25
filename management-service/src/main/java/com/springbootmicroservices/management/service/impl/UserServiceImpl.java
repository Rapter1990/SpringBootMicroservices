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

    private static final String ADVERTISEMENT_BASE_URL = "http://ADVERTISEMENT-SERVICE:9001/api/v1/user_role";
    private static final String USER_BASE_URL = "http://USER-SERVICE:9000/api/v1/users";
    private final RestTemplate restTemplate;

    @Override
    public List<Advertisement> getAllAdvertisements() {

        String result = getRoleInfo();

        if(result.equals("ROLE_USER")){
            ResponseEntity<Advertisement[]> restExchange =
                    restTemplate.getForEntity(
                            ADVERTISEMENT_BASE_URL,
                            Advertisement[].class
                    );

            return Arrays.asList(restExchange.getBody());
        }

        return null;
    }

    @Override
    public ResponseEntity<Advertisement>  getAdvertisementById(String advertisementId) {

        String result = getRoleInfo();

        if(result.equals("ROLE_USER")){
            return restTemplate.getForEntity(
                    ADVERTISEMENT_BASE_URL + "/advertisement/{advertisementId}",
                    Advertisement.class,
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
