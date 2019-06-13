package cn.xiewei.thread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferWriterTest {

    public static void main(String[] args) {

        try {
            File file = new File("f:\\javaDoc");
            file.mkdir();// 创建目录
            BufferedWriter bw = new BufferedWriter(new FileWriter("f:\\javaDoc\\test.txt"));// 在节点流FileWriter的外面再套一层处理流BufferedWriter
            for (int i = 0; i < 100; i++) {
                bw.write(String.valueOf(Math.random()));
                bw.newLine();// 调用newLine()方法使得每写入一个随机数就换行显示
            }
            bw.flush();// 调用flush()方法清空缓冲区
            String s;
            BufferedReader br = new BufferedReader(new FileReader("f:\\javaDoc\\test.txt")); // 在节点流FileReader的外面再套一层处理流BufferedReader
            while ((s = br.readLine()) != null) {
                System.out.println(br.readLine());
            }
            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
