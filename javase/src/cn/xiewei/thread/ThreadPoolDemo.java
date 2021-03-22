package cn.xiewei.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task implements Runnable {
    private String tname;

    Task(String tname) {
        this.tname = tname;
    }

    @Override
    public void run() {
        System.out.println("线程池中线程名称：" + Thread.currentThread().getName() + "=的线程==任务=" + tname + "=开始执行！");

        for (int i = 0; i < 3; i++) {
            System.out.println("线程池中线程名称：" + Thread.currentThread().getName() + "=的线程==任务======" + tname + "== " + i);
        }
        System.out.println("线程池中线程名称：" + Thread.currentThread().getName() + "=的线程=任务=" + tname + "===结束执行！");
    }
}

public class ThreadPoolDemo {
    public static void main(String[] args) {
        /*
         * 1.通过newFixedThreadPool方法可以创建尺寸固定的线程池，返回的是ExecutorService
         * 接口类型的引用，此引用指向的就是一个线程池对象
         *
         * 2.可以通过ExecutorService
         * 引用调用其execute方法，来启动线程池中的线程。其中线程任务，通过execute方法的实现了Runnable接口的对象参数来指定
         */
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
        Task task1 = new Task("任务1");
        Task task2 = new Task("任务2");
        Task task3 = new Task("任务3");
        fixedThreadPool.execute(task1);
        fixedThreadPool.execute(task2);
        fixedThreadPool.execute(task3);
        fixedThreadPool.execute(new Runnable() {
            public void run() {
                try {
                    //三个线程并发
                    Thread.sleep(7000);//线程休眠7秒钟
                    System.out.println("线程池中线程名称：" +Thread.currentThread().getName()+", 匿名内部类方式创建线程任务！");
                   
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        fixedThreadPool.shutdown();
        System.out.println("线程池关闭！");
    }
}
