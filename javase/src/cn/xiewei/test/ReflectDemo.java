package cn.xiewei.test;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, ParseException {
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //        String classPath="cn.xiewei.test.Person";
//        Class<?> clazz=Class.forName(classPath);
//        Field field=null;// clazz.getField("age");//getField方法不能访问私有属性
//        field=clazz.getDeclaredField("age");//获取age对象，要想获取age值，应该先创建对象
//        field.setAccessible(true);//对私有字段的访问取消权限检查。暴力访问
//        Object obj =clazz.newInstance();//创建person对象
//        field.set(obj, 18);//为对象中属性赋值
//        Object fieldValue= field.get(obj);//获取属性值
//        System.out.println(fieldValue);
        try {
            
     
            String expiredTime = "2020-05-31";
            long expired_time = 0;       
            expired_time = simpleDateFormat.parse(expiredTime).getTime();       
            if (expiredTime != null && !"".equals(expiredTime) && (expired_time < new Date().getTime())) {
               System.out.println("expiredTime="+expiredTime+" ,new Date().getTime()="+new Date().getTime()+" , expired_time="+expired_time);
               
            }
        } catch (Exception e) {
            e.printStackTrace();        
       }
}}
