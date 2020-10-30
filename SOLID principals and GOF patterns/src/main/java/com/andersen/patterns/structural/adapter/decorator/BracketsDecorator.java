package com.andersen.patterns.structural.adapter.decorator;

//Concrete decorator #2
public class BracketsDecorator extends Decorator {
    public BracketsDecorator(PrinterI component) {
        super(component);
    }

    @Override
    public void print() {
        System.out.print("(");
        super.print();
        System.out.print(")");
    }
}
