package cn.xiewei.annotation;
@XWTable("tb_student")
public class Student { 
    @XWField(columnName="id",type="int",length=10)
    private int id;
    
    @XWField(columnName="sname",type="varchar",length=10)
    private String studentName;
    
    @XWField(columnName="age",type="int",length=3)
    private int age; 
    public int getId() {
          return id;
    }
    public void setId(int id) {
         this.id = id;
    }
    public String getStudentName() {
         return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
         this.age = age;
    } 
 
}