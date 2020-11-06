package com.andersen.patterns.structural.decorator;

//Concrete decorator #1
public class QuotesDecorator extends Decorator {
    public QuotesDecorator(PrinterI component) {
        super(component);
    }

    @Override
    public void print() {
        System.out.print("\"");
        super.print();
        System.out.print("\"");
    }
}
