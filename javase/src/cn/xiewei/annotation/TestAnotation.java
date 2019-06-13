package cn.xiewei.annotation;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
public class TestAnotation {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException {
        Class clazz=Class.forName("cn.xiewei.annotation.Student");
        Annotation[] annotations= clazz.getAnnotations();//获取类的所有注解
        for (int i = 0; i < annotations.length; i++) {
            System.out.println(annotations[i]);
        }
        //获取类指定注解的值
        XWTable table=(XWTable)clazz.getAnnotation(XWTable.class);
        System.out.println(table.value());
        //获取类中指定属性的注解
        Field field=clazz.getDeclaredField("studentName");//反射获取指定的属性
        XWField xwField=field.getAnnotation(XWField.class);
        System.out.println(xwField.columnName()+",类型="+xwField.type()+",长度="+xwField.length());
        System.out.println("表名、列名、类型、长度都有了，就可以拼接sql语句，通过jdbc执行生成表");
    }
}
