package com.andersen.patterns.structural.composite;

//Client
public class CompositeApp {

    public static void main(String[] args) {
        CompositeShape compositeShape = new CompositeShape();
        CompositeShape compositeShape1 = new CompositeShape();
        CompositeShape compositeShape2 = new CompositeShape();

        compositeShape1.add(new Triangle());
        compositeShape1.add(new Circle());
        compositeShape1.add(new Square());

        compositeShape2.add(new Square());
        compositeShape2.add(new Square());

        compositeShape.add(compositeShape1);
        compositeShape.add(compositeShape2);

        compositeShape.draw();
    }

}
