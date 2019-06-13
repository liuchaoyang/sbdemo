package com.example.sbdemo.serialize;


import com.example.sbdemo.model.School;
import com.example.sbdemo.model.Student;

import java.io.*;
import java.util.Date;

public class SerializeTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        School school = new School("学校", "海淀");
        System.out.println(school.hashCode());

        Student user = new Student("用户一", 10, new Date(), school);
        Student copy = (Student) deepCopy(user);
        School school2 = new School("学校2", "海淀2");
        copy.setSchool(school2);

        System.out.println(user);
        System.out.println(copy);
    }

    private static void serialize() {
        School school = new School("学校", "海淀");
        System.out.println(school.hashCode());

        Student user1 = new Student("用户一", 10, new Date(), school);
        Student user2 = new Student("用户二", 20, new Date(), school);


        //序列化到文件
        File file1 = new File("user1.txt");
        File file2 = new File("user2.txt");
        try {
            file1.delete();file2.delete();
            file1.createNewFile();
            file2.createNewFile();

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file1));
            objectOutputStream.writeObject(user1);

            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file2));
            objectOutputStream.writeObject(user2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deserialize() {

        //从文件反序列化
        File file1 = new File("user1.txt");
        File file2 = new File("user2.txt");
        try {

            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file1));
            Student user1 = (Student) objectInputStream.readObject();

            objectInputStream = new ObjectInputStream(new FileInputStream(file2));
            Student user2 = (Student) objectInputStream.readObject();

            System.out.println(user1.getSchool() + "------" + user1.hashCode());
            System.out.println(user2.getSchool() + "------" + user2.hashCode());
            System.out.println(user2.getSchool().equals(user1.getSchool()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Object deepCopy(Object origin) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(origin);

        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
        Object o = objectInputStream.readObject();
        return o;
    }
}
