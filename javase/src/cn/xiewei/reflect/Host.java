package cn.xiewei.reflect;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Host {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
       Mainboard mainboard=new Mainboard();
       mainboard.run();//主机运行
       File file=new File("pci.properties");
       Properties prop=new Properties();
       FileInputStream fis=new FileInputStream(file);
       prop.load(fis);
       for (int i = 0; i < prop.size(); i++) {
          String classPath= prop.getProperty("pci"+(i+1));
          Class clazz=Class.forName(classPath);
          PCI p=(PCI)clazz.newInstance();//反射生成的对象，一定是，PCI类型的，多态
          mainboard.userPCI(p);
       }
       fis.close();
    }
}
