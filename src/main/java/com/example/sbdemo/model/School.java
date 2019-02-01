package com.example.sbdemo.model;

import java.io.Serializable;

public class School implements Serializable{

    private static final long serialVersionUID = -6868900112169021342L;
    private String name;
    private String address;

    School() {}

    public School(String name, String address) {
        this.name = name;
        this.address = address;
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
}
