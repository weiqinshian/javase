package cn.xiewei.thread;

import java.util.Random;

public class ReadWriteLockTest {
    public static void main(String[] args) {
        final Data data = new Data();
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 2; j++) {
                        data.set(new Random().nextInt(30));
                    }
                }
            }).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        data.get();
                    }
                }
            }).start();
        }
    }
}

class Data {
    private int data;// ��������

    public void set(int data) {
        System.out.println(Thread.currentThread().getName() + "׼��д������");
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.data = data;
        System.out.println(Thread.currentThread().getName() + "д��" + this.data);
    }

    public void get() {
        System.out.println(Thread.currentThread().getName() + "׼����ȡ����");
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "��ȡ" + this.data);
    }
}