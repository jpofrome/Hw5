package com.example;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Component
@EnableScheduling

public class MyFaithfulTasks {
    RestTemplate restTemplate = new RestTemplate();

    @Scheduled(cron = "45 * * * * *")
    public void eating() {
        restTemplate.getForObject("http://localhost:8080/eat/" + "yummy food", String.class);
    }

    @Scheduled(cron="50 * * * * *")
    public void studying(){
        restTemplate.getForObject("http://localhost:8080/study/" + "study material", String.class);
    }

    @Scheduled(cron="35 * * * * *")
    public void meetingWithFriends(){
        restTemplate.getForObject("http://localhost:8080/meetWithFriends/" + "where we going", String.class);
    }

    @Scheduled(cron="40 * * * * *")
    public void playing(){
        restTemplate.getForObject("http://localhost:8080/play/" + "where are we", String.class);
    }

}