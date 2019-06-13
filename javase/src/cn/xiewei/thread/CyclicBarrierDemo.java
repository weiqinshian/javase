package cn.xiewei.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {

    public static void main(String[] args) {
        /*
         * 多个线程共用一个，cyclicBarrier。 如果将3改为4，下面只new了3个线程，程序将一直等待
         */
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        ExecutorService theadPools = Executors.newFixedThreadPool(3);
        theadPools.execute(new SportsMan("运动员1", cyclicBarrier));
        theadPools.execute(new SportsMan("运动员2", cyclicBarrier));
        theadPools.execute(new SportsMan("运动员3", cyclicBarrier));

        System.out.println("跑步结束！");

    }
}

class SportsMan implements Runnable {
    private String name;
    // 一个同步辅助类，它允许一组线程互相等待，直到达到某个公共屏障点。
    private CyclicBarrier cyclicBarrier;

    public SportsMan(String name, CyclicBarrier cyclicBarrier) {
        this.name = name;
        this.cyclicBarrier = cyclicBarrier;
    }

    public void run() {
        System.out.println(this.name + "准备好了………………");
        try {
            this.cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(this.name + "开始跑步！");
    }
}
