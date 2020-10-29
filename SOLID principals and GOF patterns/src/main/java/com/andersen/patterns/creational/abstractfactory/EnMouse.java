package com.andersen.patterns.creational.abstractfactory;

public class EnMouse implements Mouse {
    @Override
    public void click() {
        System.out.println("Mouse clicked");
    }
}
