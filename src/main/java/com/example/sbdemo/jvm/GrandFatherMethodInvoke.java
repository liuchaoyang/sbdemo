package com.example.sbdemo.jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

public class GrandFatherMethodInvoke {

    private class GrandFather {
        void test() {
            System.out.println("GrandFather...");
        }
    }


    class Father extends GrandFather{
        @Override
        void test() {
            System.out.println("Father...");
        }
    }

    class Son extends Father {
        private int a;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        @Override
        void test() {
//            //1.7
//            try {
//                MethodType mt= MethodType.methodType(void.class); //方法返回类型
//                MethodHandle mh = MethodHandles.lookup().findSpecial(GrandFather.class,"test",mt,getClass());
//                mh.invoke(this);
//            } catch (Throwable throwable) {
//                throwable.printStackTrace();
//            }

            //1.8
            try {
                MethodType mt = MethodType.methodType(void.class);
                Field lookupImpl = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                lookupImpl.setAccessible(true);
                MethodHandles.Lookup lookup = (MethodHandles.Lookup) lookupImpl.get(null);
                MethodHandle mh = lookup.findSpecial(GrandFather.class,"test",mt,GrandFather.class);
                mh.invoke(this);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        Son son = new GrandFatherMethodInvoke().new Son();
        son.test();

    }
}
