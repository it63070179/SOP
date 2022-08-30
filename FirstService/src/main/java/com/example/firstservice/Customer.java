package com.example.firstservice;

public class Customer {
    private String ID;
    private String name;
    private boolean sex;
    private int age;

    public Customer(){
        this("", null, "female", 0);
    }
    public Customer(String ID, String n, String s, int a){
        setID(ID);
        setName(n);
        setAge(a);
        setSex(s);
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String  sex) {
        this.sex = sex.toLowerCase().equals("male");
    }

    public void setAge(int age) {
        if (age > 0){
            this.age = age;
        }
        else{
            this.age = 0;
        }
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public boolean getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }
}
