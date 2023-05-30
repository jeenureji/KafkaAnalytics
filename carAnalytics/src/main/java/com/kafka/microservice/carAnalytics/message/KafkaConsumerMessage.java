package com.kafka.microservice.carAnalytics.message;


import com.kafka.microservice.carAnalytics.dto.CarPostDto;
import com.kafka.microservice.carAnalytics.service.PostAnalyticalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerMessage {
    private final Logger log = LoggerFactory.getLogger(KafkaConsumerMessage.class);

    @Autowired
    private PostAnalyticalService PostService;

    @KafkaListener(topics = "car-post-topic", groupId = "Analytics-post-group")
    public void listening(@Payload CarPostDto carPostDto){
        System.out.println("received car post information : " + carPostDto);
        log.info("received car post information : {}", carPostDto);
        PostService.saveDataAnalytics(carPostDto);
    }

}
