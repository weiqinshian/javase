package cn.xiewei.reflect;
public class Mainboard {
    public void  run() {
        System.out.println("Mainboard run ...");       
    }
    public void userPCI(PCI pci) {
        if (pci!=null) {
            pci.open();
            pci.close();
        }
       
    } 
}
