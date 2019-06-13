package cn.xiewei.patterns.factory;
public class Customer {
    public static void main(String[] args) {
        //生产宝马320系列配件
        FactoryBMW320 factoryBMW320 = new FactoryBMW320();  
        factoryBMW320.createEngine();
        factoryBMW320.createAircondition();
          
        //生产宝马777系列配件  
        FactoryBMW777 factoryBMW777 = new FactoryBMW777();  
        factoryBMW777.createEngine();
        factoryBMW777.createAircondition();
    }
}
