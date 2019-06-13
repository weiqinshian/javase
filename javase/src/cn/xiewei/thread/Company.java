package cn.xiewei.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 年末公司组织团建，要求某小组5位员工到公司门口集合，统一乘坐公司所租大巴前往目的地。 在这个案例中，公司作为主线程，员工作为子线程。
 * 如果，不使用多线程，使用面向对象进行操作，也能够实现这样的输出效果，但是，很难实现员工达到目的地的随机性。
 * 
 * @author XW
 * @create_date 2017年11月15日
 */
public class Company {

    public static void main(String[] args) {

        int count = 5;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        ExecutorService threadPool = Executors.newFixedThreadPool(count);
        for (int i = 0; i < count; i++) {
            threadPool.execute(new Employee(count, countDownLatch));
        }
        try {
            countDownLatch.await();// 当前线程，主线程阻塞
            // 重载的方法，使当前线程在锁存器倒计数至零之前一直等待，除非线程被中断或超出了指定的等待时间。
            // countDownLatch.await(long timeout, TimeUnit unit)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("…………大巴车启动，载五位员工，前往目的地…………");
        threadPool.shutdown();// 关闭线程池
    }
}

class Employee implements Runnable {
    int i;
    CountDownLatch countDownLatch;

    Employee(int count, CountDownLatch countDownLatch) {
        this.i = count;
        this.countDownLatch = countDownLatch;
    }

    public void run() {

        System.out.println("员工" + i + "达到公司门口，已经上了大巴车！");
        countDownLatch.countDown();// 计数器减1
        try {
            Thread.sleep(10);// 线程睡眠10毫秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 执行coutDown()之后，继续执行自己的工作，不受主线程的影响
        System.out.println("员工" + i + "，已经在车上，做自己的事情，玩手机……");
    }
}
