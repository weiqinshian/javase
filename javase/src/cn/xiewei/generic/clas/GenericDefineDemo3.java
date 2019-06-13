package cn.xiewei.generic.clas;
import cn.xiewei.annotation.Student;
public class GenericDefineDemo3 {
    public static void main(String[] args) {
        Tool<Student> tool = new Tool<Student>();
        tool.setObject(new Student());
        //tool.setObject(new Worker());编译不通过报错
        Student stu = tool.getObject();
    }
}
