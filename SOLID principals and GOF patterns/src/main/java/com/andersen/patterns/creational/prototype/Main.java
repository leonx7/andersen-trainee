package com.andersen.patterns.creational.prototype;

public class Main {
    public static void main(String[] args) {
        Person original = new Person("Bob", 20);
        Person copy = (Person) original.clone();
        System.out.println(original);
        System.out.println(copy);

        PersonFactory factory = new PersonFactory(copy);
        Person p1 = factory.makeCopy();
        System.out.println(p1);

        factory.setPrototype(new Person("Tom", 33));
        Person p2 = factory.makeCopy();
        System.out.println(p2);
    }
}
