package cn.xiewei.thread.interrupt;
import java.io.IOException;
public class InterruptTest01 {
    public static void main(String[] args) throws IOException  {
        InterruptTest01 test = new InterruptTest01();
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
            try {
                System.out.println("进入睡眠状态");
                Thread.currentThread().sleep(10000);
                System.out.println("睡眠完毕");
            } catch (InterruptedException e) {
                System.out.println("得到中断异常");
                return;
            }finally {
                System.out.println("catch中有return，finally代码块也会执行，run方法执行完毕");
            }
            System.out.println("run方法执行完毕");
        }
    }
}
