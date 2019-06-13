package cn.xiewei.thread.interrupt;

public class InterruptTest05 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyThread1());
        thread.start();
        thread.interrupt();// 中断线程thread
    }
}

class MyThread1 implements Runnable {
    public void run() {
        try {
            for (int i = 0; i < 500; i++) {
                if (Thread.interrupted()) {
                    System.out.println("我已经是停止状态了，我要退出了！");
                    throw new InterruptedException();
                }
                System.out.println("系统输出：" + i);
                System.out.println("我被输出了，如果线程退出，我不应该被打印出来！！！");

            }
        } catch (InterruptedException e) {
            System.out.println("进入MyThread方法的catch中，线程退出");
            // e.printStackTrace();
        }
    }
}
