package com.andersen.patterns.creational.abstractfactory;

public interface DeviceFactory {
    Mouse getMouse();

    Keyboard getKeyboard();

    Touchpad getTouchpad();
}
