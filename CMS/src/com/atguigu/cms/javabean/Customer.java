package com.atguigu.cms.javabean;

public class Customer {

    private int id;
    private String name;// : 姓名
    private String gender; // 性别
    private int age;// : 年龄
    private double salary;// : 工资
    private String phone; // 电话

    public Customer() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return id + "\t" + name + (name.length() < 3 ? "\t\t" : "\t") + gender + "\t\t" + age + "\t\t" + salary + "\t\t" + phone;
    }
}
