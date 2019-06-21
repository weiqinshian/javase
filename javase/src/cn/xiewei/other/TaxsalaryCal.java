package cn.xiewei.other;

import java.math.BigDecimal;

/**
 * 新个税计算demo 
 * @author XW
 * @create_date 2019年6月21日
 */

public class TaxsalaryCal {
 private static final int DIV_SCALE = 10;//除法精度（除不尽时保留10为小数）
    
    /** 小数精确加法  */
    public static double add(double d1,double d2)
    {
        BigDecimal bd1 = BigDecimal.valueOf(d1);
        BigDecimal bd2 = BigDecimal.valueOf(d2);
        return bd1.add(bd2).doubleValue();
    }
    
    /** 小数精确减法  */
    public static double sub(double d1,double d2)
    {
        BigDecimal bd1 = BigDecimal.valueOf(d1);
        BigDecimal bd2 = BigDecimal.valueOf(d2);
        return bd1.subtract(bd2).doubleValue();
    }
    
    /** 小数精确乘法  */
    public static double mul(double d1,double d2)
    {
        BigDecimal bd1 = BigDecimal.valueOf(d1);
        BigDecimal bd2 = BigDecimal.valueOf(d2);
        return bd1.multiply(bd2).doubleValue();
    }
    
    /** 小数精确除法   */
    public static double div(double d1,double d2)
    {
        BigDecimal bd1 = BigDecimal.valueOf(d1);
        BigDecimal bd2 = BigDecimal.valueOf(d2);
        /*
         * 当除不尽时，以四舍五入的方式（关于除不尽后的值的处理方式有很多种）保留小数点后10位小数
         */
        return bd1.divide(bd2, DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    public static void main(String[] args)
    {        
        System.out.println("12月份：(13000-5000-485.34)x3%="+mul(sub(sub(13000,5000),485.34),0.03));
        System.out.println("1月份：(13000*2-5000*2-485.34*2)x3%-225.44="+sub(mul(mul(sub(sub(13000,5000),485.34),0.03),2),225.44));
        System.out.println("2月份：(13000*3-5000*3-485.34*3)x3%-225.44-225.44="+sub(sub(mul(mul(sub(sub(13000,5000),485.34),0.03),3),225.44),225.44));
        System.out.println("3月份：(13000*3+15250-5000*4-485.34*4)x3%-225.44-225.44-225.44="+sub(sub(sub(mul(sub(sub(add(mul(13000, 3),15250),mul(5000, 4)),mul(485.34, 4)),0.03),225.44),225.44),225.44));
        System.out.println("4月份：(13000*3+15250+13750-5000*5-485.34*5)x1%-225.44-225.44-225.44-292.94="+sub(sub(sub(sub(sub(mul(sub(sub(add(add(mul(13000, 3),15250),13750),mul(5000, 5)),mul(485.34, 5)),0.1),225.44),225.44),225.44),292.94),2520));
        System.out.println("5月份：(13000*3+15250+13750*2-5000*6-485.34*6)x1%-225.44-225.44-225.44-292.94-2520-568.07="+sub(sub(sub(sub(sub(sub(mul(sub(sub(add(add(mul(13000, 3),15250),mul(13750, 2)),mul(5000, 6)),mul(485.34, 6)),0.1),225.44),225.44),225.44),292.94),2520),568.07));
        System.out.println("6月份：(13000*3+15250+13750*3-5000*7-485.34*7)x1%-225.44-225.44-225.44-292.94-2520-568.07-826.47="+sub(sub(sub(sub(sub(sub(sub(mul(sub(sub(add(add(mul(13000, 3),15250),mul(13750, 3)),mul(5000, 7)),mul(485.34, 7)),0.1),225.44),225.44),225.44),292.94),2520),568.07),826.47));
        System.out.println("7月份：(13000*3+15250+13750*4-5000*8-485.34*8)x1%-225.44-225.44-225.44-292.94-2520-568.07-826.47-826.47="+sub(sub(sub(sub(sub(sub(sub(sub(mul(sub(sub(add(add(mul(13000, 3),15250),mul(13750, 4)),mul(5000, 8)),mul(485.34, 8)),0.1),225.44),225.44),225.44),292.94),2520),568.07),826.47),826.47));
        System.out.println("8月份：(13000*3+15250+13750*5-5000*9-485.34*9)x1%-225.44-225.44-225.44-292.94-2520-568.07-826.47-826.47-826.46="+sub(sub(sub(sub(sub(sub(sub(sub(sub(mul(sub(sub(add(add(mul(13000, 3),15250),mul(13750, 5)),mul(5000, 9)),mul(485.34, 9)),0.1),225.44),225.44),225.44),292.94),2520),568.07),826.47),826.47),826.46));
    }
}
