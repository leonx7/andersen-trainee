package com.andersen.patterns.structural.adapter.decorator;

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
