package com.andersen.patterns.structural.composite;

//"Leaf #3"
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing circle");
    }
}
