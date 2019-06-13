package cn.xiewei.io;

import java.io.FileReader;
import java.io.FileWriter;

public class FileWriteTest {
    public static void main(String[] args) {

        try {
            FileWriter fileWriter = new FileWriter("unicode1.txt");
            for (int i = 0; i < 65536; i++) {
                fileWriter.write(i);
            }
            fileWriter.flush();

            FileReader fileReader = new FileReader("unicode.txt");
            int read;
            while ((read = fileReader.read()) != -1) {
                System.out.println("index=" + read + ", 对应字符：" + (char) read);
            }

        } catch (Exception e) {// 路径可能不存在
            e.printStackTrace();
        }
    }

}
