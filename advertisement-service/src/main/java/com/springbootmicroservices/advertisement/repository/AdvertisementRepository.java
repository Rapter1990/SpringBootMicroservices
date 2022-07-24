package com.springbootmicroservices.advertisement.repository;

import com.springbootmicroservices.advertisement.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    Optional<Advertisement> findById(Long id);
}
