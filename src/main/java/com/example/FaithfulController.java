package com.example;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController

public class FaithfulController {

    @Timed
    @RequestMapping(value = "/eat/{food}", method = RequestMethod.GET)
    public void eating(@PathVariable("food") String food) throws IOException {
        if(food == null)
            System.out.println(" then go get food.");
        else
            System.out.println(" then go eat your food." + food + "!");
    }

    //Eating with friends
    @Timed
    @RequestMapping(value = "/eatWithFriends/{food}", method = RequestMethod.GET)
    public void eatWithFriends(@PathVariable("food") String food) throws IOException {
        if(food == null)
            System.out.println(" go get take out or something");
        else
            System.out.println(" wash hands and feast");
    }

    //Studying
    @Timed
    @RequestMapping(value = "/study/{subject}", method = RequestMethod.GET)
    public void studying(@PathVariable("subject") String subject) throws IOException {
        if(subject == null)
            System.out.println(" that you won't fail your test");
        else
            System.out.println(" that you won't fail your test");
    }

    //Meeting with friends
    @Timed
    @RequestMapping(value = "/meetWithFriends/{place}", method = RequestMethod.GET)
    public void meetWithFriends(@PathVariable("place") String place) throws IOException {
        if(place == null)
            System.out.println(" that you have friends");
        else
            System.out.println(" that you have friends");
    }

    //Playing
    @Timed
    @RequestMapping(value = "/play/{place}", method = RequestMethod.GET)
    public void playing(@PathVariable("place") String place) throws IOException {
        if(place == null)
            System.out.println(" then go outside to play");
        else
            System.out.println(" then go outside to play");
    }
}
