package cn.xiewei.reflect;

public class SoundCard implements PCI{
    public void open() {
        System.out.println("open sound card！");     
    }
    public void close() {
        System.out.println("close sound card！");
    }
}
