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

    @RequestMapping("/customerbyname/{name}")
    public Customer getCustomerByName(@PathVariable("name") String name){
        for (Customer data: customers) {
            if (name.equals(data.getName())){
                return data;
            }
        }
        return null;
    }

    @RequestMapping("/customerDelByname/{name}")
    public boolean delCustomerByName(@PathVariable("name") String n){
        for (int i = 0; i < customers.toArray().length; ++ i)  {
            if (customers.get(i).getName().equals(n)){
                return getCustomers().remove(customers.get(i));
            }
        }
        return false;
    }

    @RequestMapping("/customerDelByid/{id}")
    public boolean delCustomerByID(@PathVariable("id") String ID){
        for (int i = 0; i < customers.toArray().length; ++ i)  {
            if (customers.get(i).getID().equals(ID)){
                return getCustomers().remove(customers.get(i));
            }
        }
        return false;
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


