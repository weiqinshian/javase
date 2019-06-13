package cn.xiewei.thread.condition;

/*
jdk1.5以后将同步和锁封装成了对象。 
并将操作锁的隐式方式定义到了该对象中，
将隐式动作变成了显示动作。

Lock接口： 出现替代了同步代码块或者同步函数。将同步的隐式锁操作变成现实锁操作。
同时更为灵活。可以一个锁上加上多组监视器。
lock():获取锁。
unlock():释放锁，通常需要定义finally代码块中。


Condition接口：出现替代了Object中的wait notify notifyAll方法。
            将这些监视器方法单独进行了封装，变成Condition监视器对象。
            可以任意锁进行组合。
await();
signal();
signalAll();



*/
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Resource {
    private String name;
    private int count = 1;
    private boolean flag = false;
    // 创建一个锁对象。
    Lock lock = new ReentrantLock();
    // 通过已有的锁获取该锁上的监视器对象。
    // Condition con = lock.newCondition();
    // 通过已有的锁获取两组监视器，一组监视生产者，一组监视消费者。【1.4版本，一个锁只能获取一组监视器，因为，监视器来自继承的父类的object对象】
    Condition producer_con = lock.newCondition();
    Condition consumer_con = lock.newCondition();

    public void set(String name) {
        lock.lock();
        try {
            while (flag)
                try {
                    producer_con.await();// 生产者等待
                } catch (InterruptedException e) {
                }
            Thread.sleep(1000);
            this.name = name + count;// 烤鸭1 烤鸭2 烤鸭3
            count++;
            System.out.println(Thread.currentThread().getName() + "...生产..." + this.name);
            flag = true;
            // notifyAll();
            // con.signalAll();
            consumer_con.signal();// 唤醒消费者
        } catch (InterruptedException e) {
        } finally {
            lock.unlock();
        }

    }

    public void out() {
        lock.lock();
        try {
            while (!flag)
                try {
                    consumer_con.await();// 消费者等待
                } catch (InterruptedException e) {
                }
            Thread.sleep(1000);// 消费需要时间
            System.out.println(Thread.currentThread().getName() + "...消费......." + this.name);// 消费烤鸭1
            flag = false;
            // notifyAll();
            // con.signalAll();
            producer_con.signal();// 唤醒生产者
        } catch (InterruptedException e) {
        } finally {
            lock.unlock();
        }
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
        Resource r = new Resource();// 对公共资源进行封装
        Producer pro = new Producer(r);// 资源的引用传递给生产者，生产者生产商品，放入资源池
        Consumer con = new Consumer(r);// 资源的引用传递给消费者，消费者消费资源池中的商品
        Thread t0 = new Thread(pro, "生产者1号");// 开辟生产者1线程执行路径，先生产，后消费
        Thread t1 = new Thread(pro, "生产者2号");// 开辟生产者2线程执行路径，先生产，后消费
        Thread t2 = new Thread(con, "消费者1号");
        Thread t3 = new Thread(con, "消费者2号");
        t0.start();
        t1.start();
        t2.start();
        t3.start();
    }
}
