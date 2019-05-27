package com.example.sbdemo.spring;

import com.example.sbdemo.model.User;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InvocationTest {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        MyHello myHello = new MyHello();
        ProxyHello proxyHello = new ProxyHello(myHello);

        IHello proxyInstance = (IHello)Proxy.newProxyInstance(InvocationTest.class.getClassLoader(),
                myHello.getClass().getInterfaces(), proxyHello);

        proxyInstance.say();
    }

    private static void testInvoke() throws InvocationTargetException, IllegalAccessException {
        User user = new User();
        user.setMobile("136");

        Method[] methods = User.class.getMethods();
        for (Method method : methods) {
            if (method.getName().equals("getMobile")) {
                Object invoke = method.invoke(user, null);
                System.out.println(invoke);
            }
        }
    }

    static class ProxyHello implements InvocationHandler {

        IHello iHello;
        public ProxyHello(IHello myHello) {
            this.iHello = myHello;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Proxy start............");
            Object object = method.invoke(iHello, args);
            System.out.println("Proxy end............");
            return object;
        }
    }

    interface IHello {
        void say();
    }

    static class MyHello implements IHello {

        @Override
        public void say() {
            System.out.println("MyHello say....");
        }
    }

}
