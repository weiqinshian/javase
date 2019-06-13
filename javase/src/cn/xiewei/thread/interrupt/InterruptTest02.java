package cn.xiewei.thread.interrupt;

import java.io.IOException;

public class InterruptTest02 {
    public static void main(String[] args) throws IOException  {
        InterruptTest02 test = new InterruptTest02();
        MyThread thread = test.new MyThread();
        thread.start();
        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
             
        }
        thread.interrupt();
    } 
     
    class MyThread extends Thread{
        @Override
        public void run() {
            int i = 0;
            while(i<Integer.MAX_VALUE){
                System.out.println(i+" while循环");
                i++;
            }
        }
    }
}
