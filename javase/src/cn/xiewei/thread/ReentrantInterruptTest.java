package cn.xiewei.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantInterruptTest {
    private Lock lock = new ReentrantLock();   
    public static void main(String[] args)  {
        ReentrantInterruptTest test = new ReentrantInterruptTest();
        MyThreadT thread1 = new MyThreadT(test);
        MyThreadT thread2 = new MyThreadT(test);
        thread1.start();
        thread2.start();
         
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();
    }  
     
    public void insert(Thread thread) throws InterruptedException{
        lock.lockInterruptibly();   //注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
        try {  
            System.out.println(thread.getName()+"得到了锁");
            long startTime = System.currentTimeMillis();
            for(    ;     ;) {
                if(System.currentTimeMillis() - startTime >= Integer.MAX_VALUE)
                    break;
                //插入数据
            }
        }
        finally {
            System.out.println(Thread.currentThread().getName()+"执行finally");
            lock.unlock();
            System.out.println(thread.getName()+"释放了锁");
        }  
    }
}
class MyThreadT extends Thread {
    private ReentrantInterruptTest test = null;
    public MyThreadT(ReentrantInterruptTest test) {
        this.test = test;
    }
    @Override
    public void run() {
         
        try {
            test.insert(Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"被中断");
        }
    }
}
