package cn.xiewei.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectIoTest {
    public static void main(String[] args) {
        T t = new T();
        t.a = 111;
        try {
            FileOutputStream fos = new FileOutputStream("F:\\test.txt");
            // ObjectOutputStream流专门用来处理Object的，在fos流的外面套接ObjectOutputStream流就可以直接把一个Object写进去
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(t);// 直接把一个t对象写入到指定的文件里面
            oos.flush();
            oos.close();
            FileInputStream fis = new FileInputStream("F:\\test.txt");
            ObjectInputStream ois = new ObjectInputStream(fis); // ObjectInputStream专门用来读一个Object的
            T t2 = (T) ois.readObject();
            System.out.println(t2.a);
            System.out.println(t2.b);
            System.out.println(t2.c);
            System.out.println(t2.d);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
        } catch (ClassNotFoundException e) {
        }
    }
}

/*
 * 凡是要将一个类的对象序列化成一个字节流就必须实现Serializable接口
 * Serializable接口中没有定义方法，Serializable接口是一个标记性接口，用来给类作标记，只是起到一个标记作用。
 * 这个标记是给编译器看的，编译器看到这个标记之后就可以知道这个类可以被序列化 如果想把某个类的对象序列化，就必须得实现Serializable接口
 */
class T implements Serializable {// Serializable的意思是可以被序列化的
    int a;
    String b = "132";
    Long c = 123l;
    double d = 1.1;
    transient int e = 15;// 在声明变量时如果加上transient关键字，那么这个变量就会被当作是透明的，即不存在。
}