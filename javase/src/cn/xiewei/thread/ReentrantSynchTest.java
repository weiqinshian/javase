package cn.xiewei.thread;

public class ReentrantSynchTest implements Runnable {

    public synchronized void get() {
        System.out.println(Thread.currentThread().getName());
        set();
    }
    public synchronized void set() {
        System.out.println(Thread.currentThread().getName());
    }

    public void run() {
        get();
    }
    public static void main(String[] args) {
        ReentrantSynchTest rt = new ReentrantSynchTest();
        for(;;){
            new Thread(rt).start();
        }
    }
}
