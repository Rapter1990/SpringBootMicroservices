package com.springbootmicroservices.advertisement.service;

import com.springbootmicroservices.advertisement.entity.Advertisement;

public interface AdminService {
    Advertisement approveAdvertisement(String advertisementId);
    Advertisement rejectAdvertisement(String advertisementId);
}
