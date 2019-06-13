package cn.xiewei.thread.condition;
/**
 * 
 */

import java.io.Serializable;

/**
 * @author Terryhuang
 * @create_date 2016-3-7
 * @modify by zj 20160816
 */

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    // @GeneratedValue(strategy=GenerationType.AUTO)

    protected Integer id;
    protected String userid;
    protected String userType; // 用户类型，同步用户还是自建用�?
    protected String userName;
    protected String erpId; // protected String dpId;
    protected String mobile;
    protected String mailbox;
    protected String sex;// �?:1，女:0
    protected String status; // true:有效 false:无效
    protected String staffType;// 新建用户�?0 ，分配权限后�?1
    protected String superiorLeader; // 上级领导
    protected String position;
    protected String potograph;
    protected String employedDate;
    protected String LastLoginErrDate;// 登陆失败�?大次数时的时�?
    protected Integer LoginErrTimes;// (默认值为0)【登陆失败的次数�?

}