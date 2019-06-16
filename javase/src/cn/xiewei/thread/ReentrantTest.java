package cn.xiewei.thread;
import java.util.concurrent.locks.ReentrantLock;
public class ReentrantTest implements Runnable {
    ReentrantLock lock = new ReentrantLock();
    public void get() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        set();
        lock.unlock();
    }
    public void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        lock.unlock();
    }
    @Override
    public void run() {
        get();
    }
    public static void main(String[] args) {
        ReentrantTest reentrantTest = new ReentrantTest();
        new Thread(reentrantTest).start();
        new Thread(reentrantTest).start();
        new Thread(reentrantTest).start();
    }
}