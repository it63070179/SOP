//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.firstservice;

import java.util.Random;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneratePasswordService {
    public GeneratePasswordService() {
    }

    @RequestMapping(
            value = {"{name:[a-z]+}.generate"},
            method = {RequestMethod.GET}
    )
    public String generate(@PathVariable("name") String name) {
        Random num = new Random();
        int num_random = num.nextInt(999999999);
        return "Hi, " + name + "\nYour new Password is " + num_random;
    }
}
