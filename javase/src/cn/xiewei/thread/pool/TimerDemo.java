package cn.xiewei.thread.create;

import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo {
    public static void main(String[] args) {
        Timer timer = new Timer();
        //TimerTask类就实现了runnable接口，定时器任务，就是线程任务，使用run方法封装
        timer.schedule(new TimerTask() {
            @Override
            public void run() {// 实现定时任务
                System.out.println("timertask is run");
            }
        }, 0, 1000);
    }
}
