package com.example.sbdemo.base;

public class DataType {
    public static void main(String[] args) {
        Long l = 345L;
        Integer maxIntSigned = 2147483647; //2^31 - 1

//        System.out.println(3 >> 1); //1
//        System.out.println(3 >> 2); //0
//        System.out.println(3 >>> 1); //1

        int toRight = -2;
        System.out.println(toRight >> 1); //-1
        System.out.println(toRight >> 3); //-1
        System.out.println(toRight >>> 1);
//        System.out.println(Math.pow(2, 30) + 1);

    }
}
