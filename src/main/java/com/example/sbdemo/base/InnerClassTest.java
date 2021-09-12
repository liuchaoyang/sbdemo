package com.example.sbdemo.base;

public class InnerClassTest {

    private static String outStaticStr = "a";
    private String outStr = "a";

    public InnerClassTest() {
        System.out.println("OutClass constructor");
    }

    //https://www.cnblogs.com/gaodong/p/3608665.html
    //静态内部类可以创建静态的成员和方法，而普通内部类不可以
    //静态内部类只可以访问外部类中的静态成员变量与成员方法，而普通内部类即可以访问静态的也可以访问非静态的外部类成员方法与成员变量
    static class StaticInnoo {
        private static final int innoProp = 11;
        private String str;
        public void setStr() {
            str = outStaticStr;
        }
        public void getStr() {
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        System.out.println(StaticInnoo.innoProp);

        StaticInnoo staticInnoo = new StaticInnoo();
        staticInnoo.setStr();
        staticInnoo.getStr();

        InnerClassTest test = new InnerClassTest();
        System.out.println((test.new NormalInnoo().pss));

        int a = 2;

        //方法中的内部类可以访问方法中的final局部变量和外部类的静态变量
        class MethodInnerClass {
            void m1() {
                System.out.println(a);
                System.out.println(outStaticStr);
                //a = 3; //Variable 'a' is accessed from within inner class, needs to be final or effectively final
            }
        }
        MethodInnerClass methodInnerClass = new MethodInnerClass();
        methodInnerClass.m1();
    }

    private class NormalInnoo {
        final String normalProp = "normal final prop";
        static final String ss = "normal static final prop";
//        static String staticStr = ""; //Inner classes cannot have static declarations
        private static final String pss = "normal static final prop";
        int a = StaticInnoo.innoProp;

//        public static void staticMethod() {}; //Inner classes cannot have static declarations
    }
}
