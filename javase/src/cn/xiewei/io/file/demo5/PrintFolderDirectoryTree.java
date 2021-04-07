package cn.xiewei.io.file.demo5;

import java.io.File;

/**
 * java  层次树 结构  打印 文件夹下 所有文件
 * 
 * @author XW
 * @create_date 2021年4月7日
 */
public class PrintFolderDirectoryTree {

    public static void main(String[] args) {
//        useVerticalLinesToSplit("F:\\1.note\\1.note\\1.note");
        useSpacesToDistinguishLevels(new File("F:\\1.note\\1.note\\1.note"), "");
    }

    /**
     * 使用竖线分割
     *   
     * @author XW
     * @create_date 2021年4月7日
     * @return void
     */
    public static void useVerticalLinesToSplit(String filePath) {
        File file = new File(filePath);
        getFileName(file, 0);
    }
    
    public static void getFileName(File file, int level) {
        System.out.println(getSpace(level) + file.getName());// 文件夹名称
        File[] listFiles = file.listFiles(); // 获取指定目录下当前的所有文件夹或者文件对象
        for (int i = 0; i < listFiles.length; i++) {
            int temp = level + 1;// 标识文件的层级
            if (listFiles[i].isDirectory()) {//是否是文件夹

                getFileName(listFiles[i], temp);
            } else {
                System.out.println(getSpace(temp) + listFiles[i].getName());// 文件名称
            }
        }
    }

    private static String getSpace(int level) { // 打印文件、文件夹前的空格
        StringBuilder sb = new StringBuilder();
        sb.append("|--");
        for (int x = 0; x < level; x++) {
            sb.insert(0, "|  ");
        }
        return sb.toString();
    }

    public static StringBuffer printLevel(int level) {// 打印文件、文件夹前的空格
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < level; i++) {
            s.append("-");
        }
        return s;
    }
    /**
     * 使用空格体现文件层次
     *   
     * @author XW
     * @create_date 2021年4月7日
     * @return void
     */
   public static void useSpacesToDistinguishLevels(File file, String c){
       /**
        * 如果是文件夹,打印名称(带上制表符)
        */
       if(file.isDirectory()){
           System.out.println(c + file.getName());
       }
       /**
        * 获取所有子文件
        */
       File[] files = file.listFiles();
       for(File f : files){
           /**
            * 首先加一个制表符
            */
           String temp = c + "\t";
           if(f.isDirectory()){
               /**
                * 如果是文件夹,则进行递归
                */
               useSpacesToDistinguishLevels(f, temp);
           } else {
               /**
                * 如果不是文件夹,则直接打印
                */
               System.out.println(temp + f.getName());
           }
       }
   }

}
