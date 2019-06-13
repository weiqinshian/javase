package cn.xiewei.test;
public class Person {
   private  String name;
   private  int age;
    public Person(){
        System.out.println("执行默认构造方法！！！");
    }
    public Person(String name,int age){
        this.age=age;
        this.name=name;
        System.out.println("name="+this.name+" , age="+this.name);
    }    
    
    public void siad() {
        System.out.println("How are you today?");
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    
}
