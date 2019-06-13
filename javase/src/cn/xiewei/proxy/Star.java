package cn.xiewei.proxy;
//定义接口，明星、经纪公司都需要实现这个接口
public interface Star {
    /**
     * 面谈
     */
    void confer();
    /**
     * 签合同
     */
    void signContract();
    /**
     * 订票
     */
    void bookTicket();
    /**
     * 唱歌
     */
    void sing();
    /**
     * 收钱
     */
    void collectMoney();
}
