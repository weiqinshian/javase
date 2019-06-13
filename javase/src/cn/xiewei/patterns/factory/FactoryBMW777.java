package cn.xiewei.patterns.factory;
public class FactoryBMW777 implements AbstractFactory {      
   @Override  
   public Engine createEngine() {    
       return new EngineB();  
   }  
   @Override  
   public Aircondition createAircondition() {  
       return new AirconditionB();  
   }
}
