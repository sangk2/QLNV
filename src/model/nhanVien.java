package model;

import java.io.Serializable;

public class nhanVien implements Serializable{
    private String Id, name, birthday, email;
    private int age;
    private double salary;

    public nhanVien() {
    }
    
    public nhanVien(String Id, String name, String birthday, int age, String email, double salary) {
        this.Id = Id;
        this.name = name;
        this.birthday = birthday;
        this.age = age;
        this.email = email;
        this.salary = salary;
    }    

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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
}
