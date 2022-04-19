package com.example.httpproxy.bean;

public class APerson {
    public String name;
    public int age;
    public int id;

    public APerson(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    @Override
    public String toString() {
        return "APerson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
