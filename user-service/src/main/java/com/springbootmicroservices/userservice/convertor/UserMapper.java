package com.springbootmicroservices.userservice.convertor;

import com.springbootmicroservices.userservice.dto.SignUpRequest;
import com.springbootmicroservices.userservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static User signUpRequestToUser(SignUpRequest signUpRequest){
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(signUpRequest.getPassword());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        user.setRole(signUpRequest.getRole());
        user.setName(signUpRequest.getName());
        user.setSurname(signUpRequest.getSurname());
        user.setEmail(signUpRequest.getEmail());

        return user;
    }
}
