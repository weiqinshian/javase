package cn.xiewei.generic;
import java.util.ArrayList;
import java.util.Iterator;
public class GenericDemo {
    public static void main(String[] args) {
        ArrayList<String> al = new ArrayList<String>();
        al.add("abc");//public boolean add(Object obj)
        al.add("def====");
       //        al.add(4);//等价于al.add(new Integer(4));  
        Iterator<String> it = al.iterator();
        while(it.hasNext()){            
            String str = (String)it.next();
            System.out.println(str);
        }
    }
}
