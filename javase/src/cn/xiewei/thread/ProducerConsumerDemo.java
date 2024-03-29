package cn.xiewei.thread;

/*
两个生产者，两个消费者。

多生产者，多消费者的问题。
if判断标记，只有一次，会导致不该运行的线程运行了。出现了数据错误的情况。
while判断标记，解决了线程获取执行权后，是否要运行！【while 循环判断 标记flag，根据标记决定是否运行】

notify:只能唤醒一个线程，如果本方唤醒了本方，没有意义。而且while判断标记+notify会导致死锁。
notifyAll解决了本方线程一定会唤醒对方线程的问题。


*/

class Resource {
    private String name;
    private int count = 1;
    private boolean flag = false;

    public synchronized void set(String name)//
    {
        /*
         * 如果，用if判断，notify唤醒，下一句wait线程之后，将不再进行flag 值的判断，直接运行this.name =
         * name+count; while 解决 每次线程 唤醒之后，都必须要判断 flag 标记问题
         */
        while (flag)
            try {
                this.wait();
            } catch (InterruptedException e) {
            } // t1 t0

        this.name = name + count;// 烤鸭1 烤鸭2 烤鸭3
        count++;// 2 3 4
        System.out.println(Thread.currentThread().getName() + "...生产者..." + this.name);// 生产烤鸭1、生产烤鸭2
        flag = true;
        notifyAll();// 此处，如果用notify:只能随机唤醒一个线程，如果唤醒了本方生产者线程（生产者有两个线程），while循环判断标志位，当标志位为true，会陷入死循环
    }

    public synchronized void out()// t3
    {
        while (!flag)
            try {
                this.wait();
            } catch (InterruptedException e) {
            } // t2 t3
        System.out.println(Thread.currentThread().getName() + "...消费者........" + this.name);// 消费烤鸭1
        flag = false;
        notifyAll();
    }
}

class Producer implements Runnable {
    private Resource r;

    Producer(Resource r) {
        this.r = r;
    }

    public void run() {
        while (true) {
            r.set("烤鸭");
        }
    }
}

class Consumer implements Runnable {
    private Resource r;

    Consumer(Resource r) {
        this.r = r;
    }

    public void run() {
        while (true) {
            r.out();
        }
    }
}

class ProducerConsumerDemo {
    public static void main(String[] args) {
        Resource r = new Resource();
        Producer pro = new Producer(r);
        Consumer con = new Consumer(r);
        Thread t0 = new Thread(pro);
        Thread t1 = new Thread(pro);
        Thread t2 = new Thread(con);
        Thread t3 = new Thread(con);
        t0.start();
        t1.start();
        t2.start();
        t3.start();
    }
}
