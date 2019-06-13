package cn.xiewei.dynamicProxy;
/**
 * 动态生成的代理类，内部结构大致如下
 * 
 * @author XW
 * @create_date 2018年7月24日
 */
public class ProxyStar {

    StarHandler starHandler;
    public  ProxyStar( StarHandler starHandler) {
       this.starHandler=starHandler;
    }
    public void sing() {
//        starHandler.invoke(this, sing(), null);//调入任何一个方法，实际上，都会去调用handler中invoke方法
    }     
}
