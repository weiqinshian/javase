package cn.xiewei.generic.clas;
public class Tool<QQ>{
    private QQ q;
    public QQ getObject() {
        System.out.println("获取参数成功！！！");
        return q;
    }
    public void setObject(QQ object) {
        this.q = object;
    }   
}