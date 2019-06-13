package cn.xiewei.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableDemo {
    public static void main(String[] args) {
        try {
            MyTask myTask = new MyTask();
            // 1.执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。
            FutureTask<String> futureTask = new FutureTask<String>(myTask);
            /*
             * 2.执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。 FutureTask
             * ，继承了Runnable接口，是 Future 接口的实现类
             */
            Thread thread = new Thread(futureTask);
            thread.start();
            /*
             * 接收线程运算结果。 FutureTask 可用于 闭锁
             * 类似于CountDownLatch的作用，在所有的线程没有执行完成之后这里是不会执行的
             */
            System.out.println("========================" + futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyTask implements Callable<String> {
    Integer num;

    public String call() throws Exception {// 抛出异常
        num = 0;
        for (int i = 0; i < 100; i++) {
            num += i;
        }
        System.out.println("num:" + num);
        return num.toString();// 返回值
    }
}
