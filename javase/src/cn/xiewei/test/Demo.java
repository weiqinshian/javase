package cn.xiewei.test;

public class Demo {
          public static void main(String[] args) {  
              String setting= "caijiamiao@gfdev.com";
              String[] assCcEmailAddressArr = null ;
              if (setting!=null) {
                  assCcEmailAddressArr = setting.split("&");// 抄送邮箱地址
              }
              
              for (int i = 0; i < assCcEmailAddressArr.length; i++) {
                  System.out.println(assCcEmailAddressArr[0]);
              }
              }  
            
}
