package cn.xiewei.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class UserSemaphore {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(5); // 只能5个线程同时访问
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) { // 模拟20个客户端访问
            Runnable runnable = new Runnable() {
                public void run() {
                    try {
                        semaphore.acquire(); // 获取许可
                        Thread.sleep(2000); // 模拟实际业务逻辑
                        System.out.println(Thread.currentThread().getName() + "运行！");
                        semaphore.release(); // 访问完后，释放
                    } catch (InterruptedException e) {
                    }
                }
            };
            threadPool.execute(runnable);
        }
        threadPool.shutdown();
    }
}
