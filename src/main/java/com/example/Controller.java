package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.collections.SynchronizedQueue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import java.io.*;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Ares on 11/5/2016.
 */
@Component
@RestController
public class Controller {

    @RequestMapping(value = "/getVehicle/{id}", method = RequestMethod.GET)
    public Vehicle getVehicle (@PathVariable("id") int id) throws IOException{

        ObjectMapper map = new ObjectMapper();

        try {
            Scanner scanner = new Scanner(new File("./inventory.txt"));
            //find matching id and return vehicle
            while (scanner.hasNext()) {
                //get line from inventory and put it in v
               Vehicle v = map.readValue(scanner.nextLine(), Vehicle.class);
                //if id matches, return the vehicle
                if (v.getId() == id){
                    return v;
                }
            }

        } catch (Exception e) {
            return null;
        }


        return new Vehicle();
    }

    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public Vehicle addVehicle (@RequestBody Vehicle newVehicle) throws IOException{
        ObjectMapper map = new ObjectMapper();
        //make object to write into inventory.txt
        FileWriter output = new FileWriter("./inventory.txt", true);
        //write value into file
        map.writeValue(output, newVehicle);
        //put object into txt file with string
        FileUtils.writeStringToFile(new File("./inventory.txt"), System.lineSeparator(), UTF_8, true);
        return newVehicle;
    }

    @RequestMapping(value = "/updateVehicle", method = RequestMethod.PUT)
    public Vehicle updateVehicle (@RequestBody Vehicle newVehicle) throws IOException{

        ObjectMapper map = new ObjectMapper();
        ArrayList<Vehicle> inventory = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File("./inventory.txt"));
            //Find matching id and return vehicle
            while (scanner.hasNext()) {
                Vehicle v = map.readValue(scanner.nextLine(), Vehicle.class);
                if (v.getId() == newVehicle.getId())
                    inventory.add(newVehicle);
                else
                    inventory.add(v);
            }

            new FileOutputStream("./inventory.txt", false).close();
            for(Vehicle each: inventory){
                addVehicle(each);
            }
            return newVehicle;
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/deleteVehicle/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteVehicle (@PathVariable("id") int id) throws IOException{

        ObjectMapper map = new ObjectMapper();
        ResponseEntity response = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        ArrayList<Vehicle> inventory = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File("./inventory.txt"));
            //find matching id and return vehicle
            while (scanner.hasNext()) {
                Vehicle v = map.readValue(scanner.nextLine(), Vehicle.class);
                if(v.getId() != id)
                    inventory.add(v);
            }
            new FileOutputStream("./inventory.txt", false).close();
            for(Vehicle each: inventory){
                addVehicle(each);
            }
            return new ResponseEntity<String>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.CONFLICT);
        }
    }
}