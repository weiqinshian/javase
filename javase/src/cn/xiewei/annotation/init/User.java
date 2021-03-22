package cn.xiewei.annotation.init;

public class User
{
    private String name;
    private String age;

    public String getName()
    {
        return name;
    }

    @Init(value = "xw")
    public void setName(String name)
    {
        this.name = name;
    }

    public String getAge()
    {
        return age;
    }

    @Init(value = "29")
    public void setAge(String age)
    {
        this.age = age;
    }
}
