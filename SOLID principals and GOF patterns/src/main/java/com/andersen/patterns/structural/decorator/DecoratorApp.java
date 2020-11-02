package com.andersen.patterns.structural.decorator;

public class DecoratorApp {
    public static void main(String[] args) {
        PrinterI printer = new BracketsDecorator(new QuotesDecorator(new Printer("Hello!")));
        printer.print();
    }
}
