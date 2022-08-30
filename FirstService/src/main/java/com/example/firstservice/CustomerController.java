package com.example.firstservice;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CustomerController {
    private List<Customer> customers;

    public CustomerController(List<Customer> customers) {
        this.customers = new ArrayList<Customer>();
        this.customers.add(new Customer("1010", "John", "Male", 25));
        this.customers.add(new Customer("1018", "Peter", "Male", 24));
        this.customers.add(new Customer("1019", "Sara", "Female", 23));
        this.customers.add(new Customer("1110", "Rose", "Female", 23));
        this.customers.add(new Customer("1001", "Emma", "Female", 30));
    }
    @RequestMapping("/customers")
    public List<Customer> getCustomers() {
        return customers;
    }
    @RequestMapping("/customerbyid/{id}")
    public Customer getCustomerByID(@PathVariable("id") String ID){
        for (Customer data: customers) {
            if (ID.equals(data.getID())){
                return data;
            }
        }
        return null;
    }

    @RequestMapping("/customerbyname/{n}")
    public Customer getCustomerByName(@PathVariable("n") String n){
        for (Customer data: customers) {
            if (n.equals(data.getName())){
                return data;
            }
        }
        return null;
    }

    @RequestMapping(value = "/customerDelByname/{n}", method = RequestMethod.DELETE)
    public boolean delCustomerByName(@PathVariable("n") String n){
        for (Customer data: customers) {
            if (data.getName().equals(n)) {
                return customers.remove(data);
            }
        }
        return true;
    }

    @RequestMapping(value ="/customerDelByid/{id}", method = RequestMethod.DELETE)
    public boolean delCustomerByID(@PathVariable("id") String ID){
        for (Customer data: customers) {
            if (data.getID().equals(ID)) {
                return customers.remove(data);
            }
        }
        return true;
    }

    @RequestMapping("/addCustomer")
    public boolean addCustomer(@RequestParam("id") String ID,@RequestParam("name") String n,@RequestParam("sex") String s,@RequestParam("age") int a){
        return getCustomers().add(new Customer(ID, n, s, a));
    }

    @RequestMapping(value = "/addCustomer2", method = RequestMethod.POST)
    public boolean addCustomer2(@RequestParam("id") String ID, @RequestParam("name") String n, @RequestParam("sex") String s, @RequestParam("age") int a){
        return getCustomers().add(new Customer(ID, n, s, a));
    }

}


