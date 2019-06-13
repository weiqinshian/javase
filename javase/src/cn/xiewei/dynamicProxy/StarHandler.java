package cn.xiewei.dynamicProxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * 动态生成代理类
 * 
 * @author XW
 * @create_date 2018年7月23日
 */
public class StarHandler implements InvocationHandler {
    
    Star realStar;
    
    public StarHandler(Star star) {
        super();
        this.realStar=star;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("真正的方法执行前");
        System.out.println("代理人处理明星，座谈、签合同、预付款、订机票 等事务");
        if (method.getName().equals("sing")) {        
            method.invoke(realStar, args);//只有方法名为sing唱歌的时候，才调用明星的方法
        }
        System.out.println("真正的方法执行后");
        System.out.println("收尾款！");
        return null;
    }

}
