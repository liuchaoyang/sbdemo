package com.example.sbdemo.algorithm;

import java.util.Arrays;

public class BubbleSort {

    public static int[] bubbleSort(int[] array) {

        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if(array[j] > array[j+1]) {
                    temp = array[j];
                    array[j] =  array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] bubbleSort = BubbleSort.bubbleSort(new int[]{3, 5, 9, 5, 2, 7});
        System.out.printf("bubble:" + Arrays.toString(bubbleSort));
    }
}
