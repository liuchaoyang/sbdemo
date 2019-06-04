package com.example.sbdemo.base;

public class StaticInnerClassTest {

    public static void main(String[] args) {
        System.out.println(StaticInnoo.innoProp);

        StaticInnerClassTest test = new StaticInnerClassTest();
        System.out.println((test.new NormalInnoo().pss));
    }

    //https://www.cnblogs.com/gaodong/p/3608665.html
    //非晶态的内部类对象隐式地在外部类中保存了一个引用，指向创建它的外部类对象
    //静态内部类可以创建静态的成员而非静态的内部类不可以
    //静态内部类只可以访问外部类中的静态成员变量与成员方法而非静态的内部类即可以访问静态的也可以访问非静态的外部类成员方法与成员变量
    static class StaticInnoo {
        private static final int innoProp = 11;
    }

    private class NormalInnoo {
        final String normalProp = "normal final prop";
        static final String ss = "normal static final prop";
        private static final String pss = "normal static final prop";
        int a = StaticInnoo.innoProp;
    }
}
