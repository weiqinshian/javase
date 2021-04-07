package cn.xiewei.io.file;

import java.io.File;

public class FileTest {
    public static void main(String[] args) {

      File file = new File("G:\\git_project\\b2e_slip\\temp_file");
      File[] listFiles = file.listFiles(); // 获取指定目录下当前的所有文件夹或者文件对象
      
      for (int i = 0; i < listFiles.length; i++) {
          System.out.println(listFiles[i].getName());// 文件名称

          if (listFiles[i].isDirectory()&&!listFiles[i].getName().equals("当前日期")) {//是否是文件夹
              delFileT(listFiles[i]);//删除不是当天产生的文件夹
          } else {
          }
      }
  }
  
  /**
   * 删除文件
   * 
   * @author zj
   * @create_date 2016年9月28日
   */
  public static void delFileT(File file) {
      if (file.exists()) {
          if (file.isFile()) {
              file.delete();
          } else if (file.isDirectory()) {
              File files[] = file.listFiles();
              for (int i = 0; i < files.length; i++) {
                  delFileT(files[i]);
              }
          }
          file.delete();
      } else {
          System.out.println("所删除的文件不存在！");
      }
  }
  
 
}
