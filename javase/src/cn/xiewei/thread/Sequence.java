package cn.xiewei.thread;
public class Sequence {
    private int value;    

    public  synchronized int getNext() {
        return value ++;
    }
    public int getValue () {
        synchronized (Sequence.class) {   // monitorenter
            if(value > 0) {
                return value;
            } else {
                return -1;
            }
        }// monitorexit
    }

    public static void main(String[] args) {
        Sequence s = new Sequence();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
