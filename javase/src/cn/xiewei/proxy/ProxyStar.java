package cn.xiewei.proxy;

public class ProxyStar  implements Star {    
    private Star star;     
    public ProxyStar(Star star) {
        super();
        this.star = star;
    }
    //订票、收钱这些业务代理公司处理
    @Override
    public void bookTicket() {
    System.out.println("ProxyStar.bookTicket()");
    }      
    @Override
    public void collectMoney() {
    System.out.println("ProxyStar.collectMoney()");
    }      
    @Override
    public void confer() {
    System.out.println("ProxyStar.confer()");
    }      
    @Override
    public void signContract() {
    System.out.println("ProxyStar.signContract()");
    }
    //代理公司，处理不了唱歌这个业务，交给明星来处理 
    @Override
    public void sing() {
      star.sing();
    }
      
    }
