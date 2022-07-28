package com.springbootmicroservices.management.service.impl;

import com.springbootmicroservices.management.model.Advertisement;
import com.springbootmicroservices.management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private static final String ADVERTISEMENT_BASE_URL = "http://ADVERTISEMENT-SERVICE:9001/api/v1/user_role";
    private static final String USER_BASE_URL = "http://USER-SERVICE:9000/api/v1/users";
    private final RestTemplate restTemplate;

    @Override
    public List<Advertisement> getAllAdvertisements() {

        LOGGER.info("UserServiceImpl | getAllAdvertisements is started");

        String result = getRoleInfo();

        LOGGER.info("UserServiceImpl | getAllAdvertisements | role result : " + result);

        if(result.equals("ROLE_USER")){
            ResponseEntity<Advertisement[]> restExchange =
                    restTemplate.getForEntity(
                            ADVERTISEMENT_BASE_URL + "/alladvertisements",
                            Advertisement[].class
                    );

            LOGGER.info("UserServiceImpl | getAllAdvertisements | advertisement list size : " + Arrays.asList(restExchange.getBody().length));

            return Arrays.asList(restExchange.getBody());
        }

        return null;
    }

    @Override
    public ResponseEntity<Advertisement>  getAdvertisementById(String advertisementId) {

        LOGGER.info("UserServiceImpl | getAdvertisementById is started");

        LOGGER.info("UserServiceImpl | getAdvertisementById | advertisementId : " + advertisementId);

        String result = getRoleInfo();

        LOGGER.info("UserServiceImpl | getAdvertisementById | role result : " + result);

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

        LOGGER.info("UserServiceImpl | getRoleInfo is started");

        String result = this.restTemplate.getForObject(USER_BASE_URL + "/info",String.class);

        LOGGER.info("UserServiceImpl | getRoleInfo | role result : " + result);

        return result;
    }
}
