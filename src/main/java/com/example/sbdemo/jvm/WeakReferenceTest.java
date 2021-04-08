package com.example.sbdemo.jvm;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * 弱引用指向的对象，每次GC时，对象都会被作为垃圾回收
 */
public class WeakReferenceTest {
    public static void main(String[] args) {
        //强引用
        Demo demo = new Demo(1);

        //弱引用
        WeakReference<Demo> weakReference = new WeakReference<>(new Demo(2));
        //主动让出发GC
        System.gc();
        try {
            //给GC留点时间，保证GC执行完成
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (demo == null) {
            System.out.println("强引用指向的Demo对象 已经被回收");
        }

        //weakReference.get()获取弱引用指向的对象，如果对象是null，表示被回收
        if (weakReference.get() == null) {
            System.out.println("弱引用指向的Demo对象 已经被回收");
        }

    }
    static class Demo {
        private Integer id;
        public Demo(Integer id) {
            this.id = id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }
}
