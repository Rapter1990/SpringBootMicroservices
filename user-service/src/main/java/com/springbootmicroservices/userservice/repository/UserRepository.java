package com.springbootmicroservices.userservice.repository;

import com.springbootmicroservices.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
