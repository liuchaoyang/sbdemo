package com.example.sbdemo.serialize;

import java.io.Serializable;

public class SerialSon extends SerialFather implements Serializable {
    private String sonName = "im serial son";

    public String getSonName() {
        return sonName;
    }

    public void setSonName(String sonName) {
        this.sonName = sonName;
    }
}
