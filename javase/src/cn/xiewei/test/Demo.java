package cn.xiewei.test;

import java.nio.charset.Charset;

public class Demo {
    public static void main(String[] args) {
        String csn = Charset.defaultCharset().name();
        System.out.println("csn="+csn);
        System.out.println(System.getProperty("sun.jnu.encoding"));


    }

    /**
     * 转半角的函数(DBC case)<br/>
     * <br/>
     * 全角空格为12288，半角空格为32 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
     * https://blog.csdn.net/xyw591238/article/details/51719747
     * https://blog.csdn.net/lgywsdy/article/details/84561437
     * 
     * @param input    任意字符串
     * @return 半角字符串
     *
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                // 全角空格为12288，半角空格为32
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                // 其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

}
