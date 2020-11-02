package com.andersen.patterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

//"Composite"
public class CompositeShape implements Shape {
    private List<Shape> components = new ArrayList<>();

    @Override
    public void draw() {
        for (Shape component : components) {
            component.draw();
        }
    }

    public void add(Shape shape) {
        components.add(shape);
    }

    public void remove(Shape shape) {
        components.remove(shape);
    }
}
