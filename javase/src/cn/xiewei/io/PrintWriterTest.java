package cn.xiewei.io;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Date;

public class PrintWriterTest {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        try {
            FileWriter fw = new FileWriter("F:\\test.log", true);
            PrintWriter printWriter = new PrintWriter(fw);
            PrintStream ps = new PrintStream("");
            while ((s = br.readLine()) != null) {
                if (s.equalsIgnoreCase("exit")) {
                    break;
                }
                printWriter.println(s);// 自带换行效果
                printWriter.println(1.1);// 能够直接输出浮点数
                // fw.write(1.1);fileWriter输出浮点数会报错
            }
            fw.write(new Date().toString());
            printWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
