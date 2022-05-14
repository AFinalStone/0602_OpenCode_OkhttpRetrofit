package com.afs.okhttpretrofit.bean;

public class LoginBean {
    private String name;
    private Integer age;

    public LoginBean(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
