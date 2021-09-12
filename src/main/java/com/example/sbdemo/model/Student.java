package com.example.sbdemo.model;

import com.example.sbdemo.serialize.SerialSon;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {

    private static final long serialVersionUID = -786401198183529466L;
    private String name;
    private int age;
    private transient Date birthday;
    private School school;

    //测试序列号化
    private SerialSon serialSon;
    public static String staticStr = "I'm static str."; //不会被序列化

    static {
        System.out.println("student static block.");
    }

    public Student() {
        System.out.println("student no params construct.");
    }

    public Student(String name, int age, Date birthday, School school) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.school = school;
        System.out.println("student params construct.");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public SerialSon getSerialSon() {
        return serialSon;
    }

    public void setSerialSon(SerialSon serialSon) {
        this.serialSon = serialSon;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", school=" + school +
                '}';
    }

    //https://blog.csdn.net/moreevan/article/details/6697777
    //这个方法会在序列化的过程中被调用
    private void writeObject(ObjectOutputStream out){
        try {
            System.out.println("invoke student writeObject");
            out.defaultWriteObject(); //这个方法会把这当前中非静态变量和非transient变量写到流中
            //在这里我们可以把transient对象的属性写到了流中。
            out.writeInt(123);//ObjectOutputStream中提供了写基本类型数据的方法
            //out.close();//注意，这句千万不能有，否刚将直接导致写入失败
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //这个方法会在反序列化的过程中被调用
    private void readObject(ObjectInputStream in){
        try {
            System.out.println("invoke student readObject");
            in.defaultReadObject(); //和defaultWriteObject()方法相对应，默认的反序列化方法，会从流中读取
            //非静态变量和非transient变量
            System.out.println(in.readInt()); //用这个方法来读取一个transient对象的属性
            //in.close();同样的这句也不能有
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
