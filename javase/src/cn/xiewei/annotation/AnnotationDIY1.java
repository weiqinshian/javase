package cn.xiewei.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.METHOD, ElementType.TYPE}) //类变量和方法都可以修饰
@Retention(RetentionPolicy.RUNTIME) // RUNTIME 反射机制读取注解
public @interface AnnotationDIY1 {
    String value();  // 只有一个参数的话,一般定义为 value 
}
