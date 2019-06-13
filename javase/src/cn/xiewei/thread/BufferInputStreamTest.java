package cn.xiewei.thread;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BufferInputStreamTest {
    public static void main(String[] args) {
        try {
            // 在FileInputStream节点流的外面套接一层处理流BufferedInputStream
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
                    "D:\\Users\\Administrator\\workspace\\Demo\\src\\cn\\xiewei\\thread\\ProducerConsumerDemo.java"));
            System.out.println((char) bis.read());
            System.out.println((char) bis.read());
            int value;
            bis.mark(100);// 在第100个字符处做一个标记
            for (int i = 0; i < 10 && (value = bis.read()) != -1; i++) {
                System.out.print((char) value);
            }
            System.out.println();
            bis.reset();// 重新回到原来标记的地方
            for (int i = 0; i < 10 && (value = bis.read()) != -1; i++) {
                System.out.print((char) value);
            }
            bis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
