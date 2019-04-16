package com.example.sbdemo.base;

public class DataType {
    public static void main(String[] args) {
        Long l = 345L;
        Integer maxIntSigned = 2147483647; //2^31 - 1

//        System.out.println(3 >> 1); //1
//        System.out.println(3 >> 2); //0
//        System.out.println(3 >>> 1); //1

        int toRight = -2;
//        System.out.println(toRight >> 1); //-1
//        System.out.println(toRight >> 3); //-1
//        System.out.println(toRight >>> 1);
//        System.out.println(Math.pow(2, 30) + 1);

//    cal:1000 0001
//     bu:1111 1111
//    <<5 1110 0000
//    ^   0001 1111
//      y:31
        System.out.println(-1 ^ (-1 << 5)); //31
        System.out.println(Long.toBinaryString(System.currentTimeMillis()));
    }
}
