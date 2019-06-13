package cn.xiewei.test;

import java.io.File;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
//        String str="勇   ";    
////        for(int i = 0;i<str.length();i++){
////            char c = str.charAt(i);
////            System.out.println("c="+c+" , "+Integer.toHexString((int)c));
////            }
//       
//            System.out.println("str,size="+str.length()+" ,  "+str.trim());
//         //   String   str1 = str.replace("\\s+", "");//去除全角空格  
//            String   str1 = str.replace(" ", "");
//        //    String   counterparties1 = counterparties.replaceAll("\\s","");//去除全角空格  
//        str1=str1.trim();
//        System.out.println("str1,size="+str1.length()+" ,  "+str1.trim());F:\\2.Source
        
        java.awt.Desktop.getDesktop().open(new File("F:\\2.Source\\readMe.docx"));
    }

}

//String   counterparties1 = counterparties.replace((char)12288, ' ');
//String   counterparties1 = counterparties.replace(" ", "");
//      String   counterparties1 = str.replaceAll("\\s*", "");
