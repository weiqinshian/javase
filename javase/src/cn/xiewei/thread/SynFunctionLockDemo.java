package cn.xiewei.thread;

/*
ͬ��������ʹ�õ�����this��

ͬ��������ͬ������������
ͬ�����������ǹ̶���this��

ͬ����������������Ķ���

����ʹ��ͬ������顣


*/
class Ticket implements Runnable {
    private static int num = 100;
    boolean flag = true;

    public void run() {
        // System.out.println("this:"+this);
        if (flag)
            while (true) {
                /*
                 * ���������synchronized(this)�� ͬ������� �� ��synchronized void show()��
                 * ʹ�õĲ���ͬһ��������ô�����ǾͿ���ͬʱ���С����ͬʱ���У���ô�ͻ����������� ͬ������� �� ͬ������ ��������
                 * ͬʱ ��num ���в����������ͻ�����⡣ �� �������synchronized(this)�� ͬ������� ��
                 * ��synchronized void show()�� ʹ�õ�ͬһ��������ô��ͬһʱ�̣����� ͬ��������ͬ��������
                 * �߳�ֻ����һ���������Ͳ�������̰߳�ȫ���⡣
                 */
                synchronized (this) {// this,��ʾ��ǰ����t��Ticket t = new Ticket();
                    if (num > 0) {// �����Ʊ����0����Ʊ
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                        }
                        // ��ӡ�̵߳����֣���ʶ���ĸ��߳�����Ʊ
                        System.out.println(Thread.currentThread().getName() + ".....obj....������Ʊ��" + num--);
                    } else {
                        break;// û��Ʊ�ˣ�����ѭ��

                    }
                }
            }
        else {
            while (true) {
                this.show();// this,��ʾ��ǰ����t��Ticket t = new Ticket();
            }
        }
    }

    /**
     * ��ͬ��������ȡ������Ϊһ�������ĺ���
     */
    public static synchronized void show() {
        if (num > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
            // ��ӡ�̵߳����֣���ʶ���ĸ��߳�����Ʊ
            System.out.println(Thread.currentThread().getName() + ".....function....������Ʊ��" + num--);// ����Ʊ֮��num--���ȴ�ӡ����--
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
        t.flag = false;// �������false���ỻ�ɣ�ͬ������ִ����Ʊ����
        t2.start();
    }
}
