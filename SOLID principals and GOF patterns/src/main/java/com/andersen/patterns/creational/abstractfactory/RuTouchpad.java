package com.andersen.patterns.creational.abstractfactory;

public class RuTouchpad implements Touchpad {
    @Override
    public void track(int deltax, int deltay) {
        int s = (int) Math.sqrt(Math.pow(deltax, 2) + Math.pow(deltay, 2));
        System.out.println("Передвинуть на " + s + " пикселей");
    }
}
