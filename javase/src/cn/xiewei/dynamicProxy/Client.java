package cn.xiewei.dynamicProxy;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {
        Star real = new RealStar();//初始化明星
        StarHandler handler = new StarHandler(real);//明星进入,高级动态代理经纪公司         
        Star proxy=(Star)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),new Class[]{Star.class}, handler);
//        proxy.bookTicket();
        proxy.sing();       
        }
     }