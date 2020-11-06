package com.andersen.patterns.creational.abstractfactory;

public class RuKeyboard implements Keyboard {
    @Override
    public void print() {
        System.out.println("Печатаем строку");
    }
}
