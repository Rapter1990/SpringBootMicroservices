package com.springbootmicroservices.advertisement.convertor;

import com.springbootmicroservices.advertisement.dto.AdvertisementRequest;
import com.springbootmicroservices.advertisement.entity.Advertisement;
import org.springframework.stereotype.Component;

@Component
public class AdvertisementMapper {

    public static Advertisement advertisementRequestToAdvertisement(AdvertisementRequest advertisementRequest){
        Advertisement advertisement = new Advertisement();
        advertisement.setPrice(advertisementRequest.getPrice());
        advertisement.setTitle(advertisementRequest.getTitle());
        return advertisement;
    }
}
