package com.example;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

/**
 * Created by Ares on 11/7/2016.
 */

@Component
@EnableScheduling

public class MyTasks {
    RestTemplate restTemplate = new RestTemplate();
    int count = 1;

    @Scheduled(cron = "*/4 * * * * *")
    public void addVehicle() {
        Random r = new Random();
        int id = count;
        int year = r.nextInt(30) + 1986;
        double retailPrice = r.nextInt(30000) + 15000;
        String makeModel = "";
        for (int i = 0; i < 20; i++)
            makeModel += (char) ((char) (r.nextInt(122 - 65) + 65));
        Vehicle randomV = new Vehicle(id, makeModel, year, retailPrice);
        restTemplate.postForObject("http://localhost:8080/addVehicle", randomV, Vehicle.class);
        count++;
    }

    @Scheduled(cron = "*/6 * * * * *")
    public void deleteVehicle() {
        try {
            Random r = new Random();
            int id = getRandomNumberInRange(0, 100);

            Vehicle randomV = restTemplate.getForObject("http://localhost:8080/getVehicle/" + id, Vehicle.class);

            restTemplate.delete("http://localhost:8080/deleteVehicle/" + id);

        } catch (Exception e){
            System.out.println("There are no vehicles to be deleted");
        }
    }

    @Scheduled(cron = "*/4 * * * * *")
    public void updateVehicle(){
        try{
            Random r = new Random();
            int id = getRandomNumberInRange(0, 10);
            Vehicle vehicle = new Vehicle(id, "mod", 0, 0.0);

            restTemplate.put("http://localhost:8080/updateVehicle/", vehicle, Vehicle.class);
            Vehicle updateV = restTemplate.getForObject("http://localhost:8080/getVehicle/" + vehicle.getId(), Vehicle.class);


        } catch (Exception e){
            System.out.println("There are no vehicles to be deleted");
        }
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}