package cn.xiewei.annotation.init;

import java.lang.reflect.InvocationTargetException;

public class Test
{
    public static void main(String[] args) throws IllegalAccessException,
            IllegalArgumentException, InvocationTargetException
    {
        User user = UserFactory.create();

        System.out.println(user.getName());
        System.out.println(user.getAge());
    }
}
