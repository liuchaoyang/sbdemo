package com.example.sbdemo.algorithm;

import java.util.Arrays;
import java.util.LinkedList;

public class SinglyLinkedList {
    private SinglyLinkedNode head;
    private SinglyLinkedNode tail;

    public void add(SinglyLinkedNode n) {
        System.out.println(n);
        if (head == null || tail == null) {
            head = tail = n;
            return;
        }
        tail = tail.next = n;
        return;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        SinglyLinkedNode cur = head;
        while (cur != null) {
            stringBuilder.append(cur.key).append(",");
            cur = cur.next;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
//        SinglyLinkedList list = new SinglyLinkedList();
//        list.add(new SinglyLinkedNode(1));
//        list.add(new SinglyLinkedNode(2));
//        System.out.println(list.toString());
//        list.add(new SinglyLinkedNode(3));
//        list.add(new SinglyLinkedNode(4));
//        list.add(new SinglyLinkedNode(5));
//        System.out.println(list.toString());
    }
}
