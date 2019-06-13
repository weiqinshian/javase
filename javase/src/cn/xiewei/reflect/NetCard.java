package cn.xiewei.reflect;

public class NetCard implements PCI{
    public void open() {
        System.out.println("open net card！");     
    }
    public void close() {
        System.out.println("close net card！");
    }
}
