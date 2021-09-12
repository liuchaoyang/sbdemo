package com.example.sbdemo.model;

import java.io.Serializable;

public class School implements Serializable{

    private static final long serialVersionUID = -6868900112169021342L;
    private String name;
    private String address;

    static {
        System.out.println("school static block.");
    }

    School() {
        System.out.println("School no params construct.");
    }

    public School(String name, String address) {
        this.name = name;
        this.address = address;
        System.out.println("School params construct.");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
