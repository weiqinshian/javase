package cn.xiewei.test.serializables;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
public class ObjfSerializeAndDeserializeTest {
    public static void main(String[] args) {  
        
        // 反序列化生成Person对象  
        Person person = DeserializePerson();  
        System.out.println("name :" + person.getName());  
        System.out.println("age  :" + person.getAge());  
    }  
  
    /** 
     * 执行反序列化过程生产Person对象 
     *   
     * @author XW
     * @create_date 2019年6月5日
     * @return Person
     */     
    private static Person DeserializePerson() {  
  
        Person person = null;  
        ObjectInputStream inputStream = null;  
        try {  
            inputStream = new ObjectInputStream(new FileInputStream("E:/hello.txt"));  
            try {  
                person = (Person) inputStream.readObject();  
                System.out.println("执行反序列化过程成功。");  
            } catch (ClassNotFoundException e) {  
                e.printStackTrace();  
            }  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                inputStream.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return person;  
    }  
}
