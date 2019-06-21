package com.example.sbdemo.spring;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * https://www.runoob.com/design-pattern/proxy-pattern.html
 */
public class MyCglibProxy {
    public static void main(String[] args) throws Exception {
        // 生成 Cglib 代理类
        Engineer engineerProxy = (Engineer) CglibProxy.getProxy(new Engineer());
        // 调用相关方法
        engineerProxy.eat();
    }

    static class Engineer {
        // 可以被代理
        public void eat() {
            System.out.println("工程师正在吃饭");
        }

        // final 方法不会被生成的字类覆盖
        public final void work() {
            System.out.println("工程师正在工作");
        }

        // private 方法不会被生成的字类覆盖
        private void play() {
            System.out.println("this engineer is playing game");
        }
    }

    static class CglibProxy implements MethodInterceptor {
        private Object target;

        public CglibProxy(Object target) {
            this.target = target;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("###   before invocation");
            Object result = method.invoke(target, objects);
            System.out.println("###   end invocation");
            return result;
        }

        public static Object getProxy(Object target) {
            Enhancer enhancer = new Enhancer();
            // 设置需要代理的对象
            enhancer.setSuperclass(target.getClass());
            // 设置代理人
            enhancer.setCallback(new CglibProxy(target));
            return enhancer.create();
        }
    }
}

