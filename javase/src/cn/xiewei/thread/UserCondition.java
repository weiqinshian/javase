package cn.xiewei.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserCondition {
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void method1() {
        try {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "加锁……");
            lock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "等待……");
            condition.await();// Object wait，释放锁，线程马上进入阻塞状态
            System.out.println("当前线程：" + Thread.currentThread().getName() + "继续执行……");

        } catch (InterruptedException e) {
        } finally {
            lock.unlock();
        }
    }

    public void method2() {
        try {
            System.out.println("当前线程：" + Thread.currentThread().getName() + "加锁……");
            lock.lock();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "发出唤醒……");
            condition.signal();// 相当于Object notify，不释放锁，线程执行完毕后才退出
            System.out.println("当前线程：" + Thread.currentThread().getName() + "继续执行……");
        } catch (Exception e) {
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        UserCondition userCondition = new UserCondition();
        new Thread(new Runnable() {
            public void run() {
                userCondition.method1();
            }
        }, "t1").start();
        new Thread(new Runnable() {
            public void run() {
                userCondition.method2();
            }
        }, "t2").start();
    }
}
