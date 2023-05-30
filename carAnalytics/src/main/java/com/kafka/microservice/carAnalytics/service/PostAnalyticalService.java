package com.kafka.microservice.carAnalytics.service;

import com.kafka.microservice.carAnalytics.entity.BrandAnalyticsEntity;
import com.kafka.microservice.carAnalytics.entity.CarModelEntity;
import com.kafka.microservice.carAnalytics.entity.CarModelPriceAnalytics;
import com.kafka.microservice.carAnalytics.repository.BrandAnalyticsRepo;
import com.kafka.microservice.carAnalytics.repository.CarModelAnalyticsRepo;
import com.kafka.microservice.carAnalytics.repository.CarModelPriceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kafka.microservice.carAnalytics.dto.CarPostDto;

@Service
public class PostAnalyticalService {

    @Autowired
    BrandAnalyticsRepo brandAnalyticsRepo;

    @Autowired
    CarModelAnalyticsRepo carModelAnalyticsRepo;

    @Autowired
    CarModelPriceRepo carModelPriceRepo;

    public void saveDataAnalytics(CarPostDto carPostDto){
        saveBrandAnalytics(carPostDto.getBrand());
        saveCarModelAnalytics(carPostDto.getModel());
        saveCarModelPriceAnalytics(carPostDto.getModel(), carPostDto.getPrice());

    }

    public void saveBrandAnalytics(String brand) {
        BrandAnalyticsEntity brandAnalyticsEntity = new BrandAnalyticsEntity();
        brandAnalyticsRepo.findByBrand(brand).ifPresentOrElse(a -> {
            a.setPosts(a.getPosts() + 1);
            brandAnalyticsRepo.save(a);

        }, () -> {
            brandAnalyticsEntity.setBrand(brand);
            brandAnalyticsEntity.setPosts(1L);
            brandAnalyticsRepo.save(brandAnalyticsEntity);
        });

    }

    public void saveCarModelAnalytics(String model){
        CarModelEntity carModelEntity = new CarModelEntity();
        carModelAnalyticsRepo.findByModel(model).ifPresentOrElse(a -> {
            a.setPosts(a.getPosts()+1);
            carModelAnalyticsRepo.save(a);
        }, () -> {
            carModelEntity.setModel(model);
            carModelEntity.setPosts(1L);
            carModelAnalyticsRepo.save(carModelEntity);
        });
    }

    public void saveCarModelPriceAnalytics(String model, Double price){
        CarModelPriceAnalytics carModelPriceAnalytics = new CarModelPriceAnalytics();
        carModelPriceAnalytics.setModel(model);
        carModelPriceAnalytics.setPrice(price);
        carModelPriceRepo.save(carModelPriceAnalytics);
    }
}
