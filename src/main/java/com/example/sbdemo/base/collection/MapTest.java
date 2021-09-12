package com.example.sbdemo.base.collection;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {

    public static void linkedHashMap() {
        int size = 5;
        int Size = 5;

        /**
         * false, 基于插入排序
         * true, 基于访问排序
         */
        Map<String, String> map = new LinkedHashMap<String, String>(size, .75F,
                true) {

            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                boolean tooBig = size() > size;

                if (tooBig) {
                    System.out.println("最近最少使用的key=" + eldest.getKey());
                }
                return tooBig;
            }
        };

        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.put("4", "4");
        map.put("5", "5");
        System.out.println(map.toString());

        map.put("6", "6");
        map.get("2");
        map.put("7", "7");
        map.get("4");

        System.out.println(map.toString());
    }


    public static void hashMap() {
        Map map = new HashMap(1000);
        map.put("a", "b");
        //注："420112199111183939".hashCode(); -->结果是：-61
        map.put("420112199111183939", "b");

        map.get("a");
    }

    public static void concurrentHashMap() {
        Map map = new ConcurrentHashMap();
        map.put(Integer.valueOf(0), "b");

        map.get("a");
    }

    public static void weakHashMap() {
        WeakHashMap map = new WeakHashMap();
        map.put("a", "b");

        map.get("a");
    }

    public static void main(String[] args) throws Exception {
//        linkedHashMap();
//        concurrentHashMap();
        weakHashMap();

    }

}
