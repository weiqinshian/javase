package cn.xiewei.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class PrintStreamTest {
    public static void main(String[] args) {
        print("D:\\Users\\Administrator\\workspace\\Demo\\src\\cn\\xiewei\\io\\PrintStreamTest.java", System.out);
        // print(args[0], System.out);
    }

    public static void print(String fileName, PrintStream printStream) {// 传入system.out
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String string = null;
            while ((string = br.readLine()) != null) {
                printStream.println(string);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
        }
    }
}
