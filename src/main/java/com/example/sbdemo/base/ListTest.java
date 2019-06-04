package com.example.sbdemo.base;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {

    private int a;
    public void arrayList() {
//        System.out.println(a + 1);
        List list = new ArrayList<>();
        list.add("a");
        list.add(3);

        list.get(0);
        System.out.println(list);
    }

    public void vector() {
        Vector list = new Vector();
        list.add("a");
        list.add(3);

        list.get(0);
        System.out.println(list);
    }

    public void copyAndWrite() {
        List list = new CopyOnWriteArrayList();
        list.add("a");
        list.add(3);

        list.get(0);
        System.out.println(list);
    }

    public void linkedList() {
        List list = new LinkedList();
        list.add("a");
        list.add(3);
        list.add(1, "cc");

        list.get(0);
        System.out.println(list);
    }

    public void hashSet() {
        HashSet hashSet = new HashSet();
        hashSet.add("a");
        hashSet.add(3);
        hashSet.add("cc");

        hashSet.isEmpty();
        System.out.println(hashSet);
    }

    public static void main(String[] args) {
        ListTest t = new ListTest();
//        t.arrayList();
        System.out.println(6 >> 1);
        System.out.println(5 >> 1);
    }
}
