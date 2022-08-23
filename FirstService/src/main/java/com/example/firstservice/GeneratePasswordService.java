package com.example.firstservice;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class GeneratePasswordService {
    @RequestMapping(value = "{name:[a-z]+}.generate", method = RequestMethod.GET)
    public String generate(@PathVariable("name") String name){
        Random num = new Random();
        int num_random = num.nextInt();
        return "Hi, " + name + "\n" + "Your new Password is " + num_random;
    }
}
