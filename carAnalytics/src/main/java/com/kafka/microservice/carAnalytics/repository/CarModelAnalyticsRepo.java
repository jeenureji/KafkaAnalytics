package com.kafka.microservice.carAnalytics.repository;

import com.kafka.microservice.carAnalytics.entity.CarModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarModelAnalyticsRepo extends JpaRepository<CarModelEntity, Long> {
    Optional<CarModelEntity> findByModel(String model);
}
