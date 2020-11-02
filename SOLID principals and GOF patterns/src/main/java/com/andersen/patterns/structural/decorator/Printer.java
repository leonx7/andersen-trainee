package com.andersen.patterns.structural.decorator;

//Concrete component
public class Printer implements PrinterI {
    String value;

    public Printer(String value) {
        this.value = value;
    }

    @Override
    public void print() {
        System.out.print(value);
    }
}
