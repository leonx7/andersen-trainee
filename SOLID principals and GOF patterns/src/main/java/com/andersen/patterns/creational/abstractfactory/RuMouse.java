package com.andersen.patterns.creational.abstractfactory;

public class RuMouse implements Mouse {
    @Override
    public void click() {
        System.out.println("Щелчок мышью");
    }
}
