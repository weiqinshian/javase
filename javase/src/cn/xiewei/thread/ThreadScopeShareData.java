package cn.xiewei.thread;

public class ThreadScopeShareData {

    public static String date = "共享数据:";

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread() {
                public void run() {
                    date = date + Thread.currentThread().getName();
                    System.out.println("线程名称：" + Thread.currentThread().getName() + "-----put的线程数据：" + date);
                    System.out.println("A类获取数据：" + new A1().getDate());
                }
            }.start();
        }
    }

    static class A1 {
        public String getDate() {
            return date;
        }

        public void setDate(String dates) {
            date = dates;
        }
    }
}
