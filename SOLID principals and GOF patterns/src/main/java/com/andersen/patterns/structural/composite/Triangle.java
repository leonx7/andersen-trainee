package com.andersen.patterns.structural.composite;

//"Leaf #2"
public class Triangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing triangle");
    }
}
