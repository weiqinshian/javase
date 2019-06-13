package cn.xiewei.test;

import java.io.File;

public class Demo {
          public static void main(String[] args) {  
                  File file=new File("G:\\git_project\\AdvanceNotebook\\note");  //G:\\git_project\\AdvanceNotebook\\note
                  
                  getFileName(file,0);          
              }  
              public static void getFileName(File file,int level){  
                 System.out.println(getSpace(level)+file.getName());//文件夹名称  
                 File[] listFiles= file.listFiles();     //获取指定目录下当前的所有文件夹或者文件对象  
                 for(int i=0;i<listFiles.length;i++)  
                {  
                     int temp=level+1;//标识文件的层级  
                     if(listFiles[i].isDirectory())                
                    {  
                        
                         getFileName(listFiles[i],temp);  
                     }else  
                    {  
                        System.out.println(getSpace(temp)+listFiles[i].getName());//文件名称  
                    }                 
                 }  
             }  
             private static String getSpace(int level) { //打印文件、文件夹前的空格    
               StringBuilder sb = new StringBuilder();       
                sb.append("|--");  
                for(int x=0; x<level; x++){  
                     sb.insert(0,"|  ");  
                 }         
                 return sb.toString();  
             }  
             public static StringBuffer printLevel(int level){//打印文件、文件夹前的空格  
                 StringBuffer s=new StringBuffer();  
                 for(int i=0;i<level;i++){  
                     s.append("-");  
                 }  
                 return s;  
             }  
}
