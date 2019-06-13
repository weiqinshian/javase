package cn.xiewei.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThirdThread {
    public static void main(String[] args) {
        // 创建Callable对象
        ThirdThread rt = new ThirdThread();
        // 先使用Lambda表达式创建Callable<Integer>对象
        // 使用FutureTask(直译，未来的任务)来包装Callable对象
        FutureTask<Integer> task = new FutureTask<Integer>((Callable<Integer>) () ->
        {
            int i = 0;
            for (; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " 的循环变量i的值：" + i);
            }
            // call()方法可以有返回值
            return i;
        });

        /*
         * i=20的时候创建一个子线程
         */
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " 的循环变量i的值：" + i);
            if (i == 20) {
                // 实质还是以Callable对象来创建、并启动线程
                new Thread(task, "有返回值的线程" + i).start();
            }
        }
        try {
            /*
             * 调用get方法，获取线程返回值Callable任务中call方法的返回值，必须等到线程结束后才能获取返回值，故而，
             * 该方法会导致程序阻塞
             */
            System.out.println("子线程的返回值：" + task.get());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
