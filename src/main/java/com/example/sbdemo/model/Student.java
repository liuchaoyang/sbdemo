package com.example.sbdemo.model;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {

    private static final long serialVersionUID = -786401198183529466L;
    private String name;
    private int age;
    private transient Date birthday;
    private School school;

    public Student(String name, int age, Date birthday, School school) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.school = school;
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

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", school=" + school +
                '}';
    }
}
