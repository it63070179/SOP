package com.example.lab4;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cashier {
    @RequestMapping(value = "/getChange/{amount}", method = RequestMethod.GET)
    public Change getChange(@PathVariable("amount") int amount) {
        Change c = new Change();
        c.setB1000(amount/1000);
        amount = amount % 1000;
        c.setB500(amount/500);
        amount = amount % 500;
        c.setB100(amount/100);
        amount = amount % 100;
        c.setB20(amount/20);
        amount = amount % 20;
        c.setB10(amount/10);
        amount = amount % 10;
        c.setB5(amount/5);
        amount = amount % 5;
        c.setB1(amount);
        return c;
    }

}
