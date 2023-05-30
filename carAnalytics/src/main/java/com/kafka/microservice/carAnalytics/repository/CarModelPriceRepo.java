package com.kafka.microservice.carAnalytics.repository;

import com.kafka.microservice.carAnalytics.entity.CarModelPriceAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarModelPriceRepo extends JpaRepository<CarModelPriceAnalytics, Long> {
}
