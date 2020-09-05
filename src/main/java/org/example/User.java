package org.example;

import java.io.Serializable;

/**
 * @Author Dajie
 * @create 2020/9/5 9:40
 */
public class User implements Serializable {
    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
