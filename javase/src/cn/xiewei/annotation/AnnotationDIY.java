package cn.xiewei.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target(value = {ElementType.METHOD, ElementType.TYPE}) //类变量和方法都可以修饰
@Retention(RetentionPolicy.RUNTIME) // RUNTIME 反射机制读取注解
public @interface AnnotationDIY {
//  String hometown();  // hometown 是参数名, String 是参数的类型. 不给默认值,使用注解时,要赋一个值
    String hometown() default "";  // default 表示给参数一个默认值
    int distance() default 0;
    int id() default -1;   //不给id传值得话, id就不存在
    String[] rivers() default {"changjiang", "huanghe"};   
}

