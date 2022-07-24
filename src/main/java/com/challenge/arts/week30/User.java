package com.challenge.arts.week30;

/**
 * 用户
 */
public class User extends AbstractUser implements Run{

    public String name;

    public Integer age;

    public User() {
        this("Java", 10);
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public void eating() {
        System.out.printf("User eating...");
    }

    @Override
    public void running() {
        System.out.printf("User running....");
    }
}
