package cn.xiewei.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputStreamReaderTest {
    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = null;
        try {
            s = br.readLine();
            while (s != null) {
                System.out.println(s);
                s = br.readLine();
                if (s.equalsIgnoreCase("EXIT")) {// 检测到输入EXIT就退出程序
                    break;// 退出循环
                }
            }
            br.close();
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
