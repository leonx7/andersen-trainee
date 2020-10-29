package com.andersen.patterns.creational.prototype;

public class Person implements Copyable {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public Object clone() {
        return new Person(name, age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age + ", " + this.hashCode() +
                '}';
    }
}
