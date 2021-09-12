package com.example.sbdemo.base.collection;

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

    public void treeSet() {
        TreeSet treeSet = new TreeSet();
        treeSet.add("a");
        treeSet.add("b");

        System.out.println(treeSet);
    }

    public static void main(String[] args) {
        ListTest t = new ListTest();
        t.foreachAndRemove();
    }

    public void foreachAndRemove() {
        List<String> list = new ArrayList<>();
        list.add("元素一");
        list.add("元元素2");
        list.add("元元素3");
        list.add("元素4");
        for (String s : list) {
            if (s.startsWith("元元素")) {
//                list.remove(s); //ConcurrentModificationException
            }
        }
        list.removeIf(s -> s.startsWith("元元素"));
        System.out.printf(list + "");

    }

}
