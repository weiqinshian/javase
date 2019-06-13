package cn.xiewei.annotation;

public class AnnotationDIYDemo {
    @AnnotationDIY(distance = 10000, hometown = "Chongqing", id = 23)   //为注解中的参数赋值
    public void goHome() {
    }
//  @AnnotationDIY1(value = "you")
    @AnnotationDIY1("you")   //不加value参数名也可以
    public void missYou() {

    }
}
