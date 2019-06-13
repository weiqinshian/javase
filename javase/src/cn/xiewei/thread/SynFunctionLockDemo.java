package cn.xiewei.thread;

/*
同步函数的使用的锁是this；

同步函数和同步代码块的区别：
同步函数的锁是固定的this。

同步代码块的锁是任意的对象。

建议使用同步代码块。


*/
class Ticket implements Runnable {
    private static int num = 100;
    boolean flag = true;

    public void run() {
        // System.out.println("this:"+this);
        if (flag)
            while (true) {
                /*
                 * 【如果，“synchronized(this)” 同步代码块 和 “synchronized void show()”
                 * 使用的不是同一个锁，那么，他们就可以同时运行。如果同时运行，那么就会出现这种情况 同步代码块 和 同步函数 里面的语句
                 * 同时 对num 进行操作，这样就会出问题。 】 如果，“synchronized(this)” 同步代码块 和
                 * “synchronized void show()” 使用的同一个锁。那么，同一时刻，进入 同步代码块和同步函数的
                 * 线程只能有一个，这样就不会出现线程安全问题。
                 */
                synchronized (this) {// this,表示当前对象t，Ticket t = new Ticket();
                    if (num > 0) {// 如果，票大于0，买票
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                        }
                        // 打印线程的名字，标识是哪个线程卖的票
                        System.out.println(Thread.currentThread().getName() + ".....obj....卖掉了票：" + num--);
                    } else {
                        break;// 没有票了，结束循环

                    }
                }
            }
        else {
            while (true) {
                this.show();// this,表示当前对象t，Ticket t = new Ticket();
            }
        }
    }

    /**
     * 将同步代码块抽取出来成为一个独立的函数
     */
    public static synchronized void show() {
        if (num > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            // 打印线程的名字，标识是哪个线程卖的票
            System.out.println(Thread.currentThread().getName() + ".....function....卖掉了票：" + num--);// 卖完票之后，num--，先打印，再--
        }
    }
}

public class SynFunctionLockDemo {
    public static void main(String[] args) {
        Ticket t = new Ticket();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t1.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
        }
        t.flag = false;// 参数变更false，会换成，同步函数执行卖票任务
        t2.start();
    }
}
