package com.example.sbdemo.serialize;

import com.alibaba.fastjson.JSON;
import com.example.sbdemo.model.School;
import com.example.sbdemo.model.Student;

import java.io.*;
import java.util.Date;

public class DeepCopy {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        School school = new School("学校", "海淀");
        System.out.println(school.hashCode());
        Student user = new Student("用户一", 10, new Date(), school);

        Student copy = (Student) deepCopyByJson(user);

        School school2 = new School("学校2", "海淀2");
        copy.setSchool(school2);

        System.out.println(user);
        System.out.println(copy);
    }

    private static Object deepCopy(Object origin) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(origin);

        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        Object o = objectInputStream.readObject();
        return o;
    }

    private static Student deepCopyByJson(Object obj) {
        String json = JSON.toJSONString(obj);
        return JSON.parseObject(json, Student.class);
    }

}
