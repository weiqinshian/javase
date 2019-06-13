package cn.xiewei.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class OutputStreamWriterTest {

    public static void main(String[] args) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("e:\\test.txt"));
            osw.write("学海无涯，维勤是岸！！！");
            System.out.println("文件默认编码：" + osw.getEncoding());// 使用getEncoding()方法取得当前系统的默认字符编码
            osw.close();
            /*
             * 如果在调用FileOutputStream的构造方法时没有加入true，那么新加入的字符串就会替换掉原来写入的字符串，
             * 在调用构造方法时指定了字符的编码,新写入的字符，会使用新指定的编码
             */
            osw = new OutputStreamWriter(new FileOutputStream("e:\\test.txt", true), "ISO8859_1");
            osw.write("他山之石，可以攻玉！！！");
            System.out.println("修改文件编码之后getEncoding：" + osw.getEncoding());
            osw.close();
            System.out.println(codeString("e:\\test.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {

        }
    }

    public static String codeString(String fileName) throws Exception {
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));
        int p = (bin.read() << 8) + bin.read();
        String code = null;
        // 其中的 0xefbb、0xfffe、0xfeff、0x5c75这些都是这个文件的前面两个字节的16进制数
        switch (p) {
        case 0xefbb:
            code = "UTF-8";
            break;
        case 0xfffe:
            code = "Unicode";
            break;
        case 0xfeff:
            code = "UTF-16BE";
            break;
        case 0x5c75:
            code = "ANSI|ASCII";
            break;
        default:
            code = "GBK";
        }
        return code;
    }
}
