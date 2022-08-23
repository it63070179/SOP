package com.example.firstservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class demo_service {
    @RequestMapping(value = "/helloWorld", method = RequestMethod.GET)
    public String helloWorld() {
        return "Hello World";
    }
}
