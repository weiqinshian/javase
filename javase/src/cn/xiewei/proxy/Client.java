package cn.xiewei.proxy;
public class Client {
    public static void main(String[] args) {
        Star real = new RealStar();//初始化明星
        Star proxy = new ProxyStar(real);//明星进入经纪公司         
        proxy.confer();//多态，方法执行，编译看左边，运行看右边，实际执行都是经纪公司方法
        proxy.signContract();
        proxy.bookTicket();
        proxy.sing();         
        proxy.collectMoney();
         
        }
     }