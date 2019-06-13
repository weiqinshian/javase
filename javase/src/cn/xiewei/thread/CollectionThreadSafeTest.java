package cn.xiewei.thread;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;

public class CollectionThreadSafeTest {

    public static void main(String[] args) {
        /*
         * 进行十次测试，每次创建100个线程，每个线程往共享数据（集合）中，写入，10个数据，
         * 故而，如果是线程安全的，最后，每一次输出结果，集合尺寸都应该是1000
         */
        for (int i = 0; i < 10; i++) {
            createThread();
        }
    }

    static void createThread() {
        try {
            int threadCount = 100;// 创建线程数量
            CountDownLatch countDownLatch = new CountDownLatch(threadCount);
            List<Object> lists = new Vector<Object>();
            // List<Object> lists = new ArrayList<Object>();
            for (int i = 0; i < threadCount; i++) {
                new Thread(new SetNum(lists, countDownLatch)).start();
            }
            countDownLatch.await();// 主线程等待所有子线程都执行完再执行
            System.out.println("集合lists.size()=" + lists.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SetNum implements Runnable {

    private List<Object> lists;
    private CountDownLatch countDownLatch;

    SetNum(List<Object> list, CountDownLatch countDownLatch) {
        this.lists = list;
        this.countDownLatch = countDownLatch;
    }

    public void run() {
        /*
         * 每个线程向list中添加10个对象，这里，是多线程访问的代码块，而且， 操作了共享数据list，可能会出现线程安全问题。
         * 故而，循环，次数不要太多，否则，如果，共享数据是ArrayList、hashmap等线程不安全的集合，会出现，下标越界问题。
         * 因为，集合是可变长度的，在线程写人的时候，集合内部，还没有实现，集合长度的扩展，故而，出现下标越界问题
         */
        for (int i = 0; i < 10; i++) {
            lists.add(new Object());
        }
        // 完成一个子线程，计数器减一
        countDownLatch.countDown();
    }

}
