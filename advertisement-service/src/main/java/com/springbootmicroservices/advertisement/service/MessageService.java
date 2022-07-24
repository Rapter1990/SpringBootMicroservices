package com.springbootmicroservices.advertisement.service;

import com.springbootmicroservices.advertisement.entity.Advertisement;

public interface MessageService {
    public void sendMessage(Advertisement advertisement);
}
