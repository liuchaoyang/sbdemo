package com.example.sbdemo.base;

public class Fanxing<T> {

    private T item;

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public static void main(String[] args) {
        Fanxing<String> fanxing = new Fanxing<>();
        fanxing.setItem("items");

        //编译后泛型变成强制转换，即运行期List<int>和List<String>是同一个类
        String item = fanxing.getItem();
    }


}
