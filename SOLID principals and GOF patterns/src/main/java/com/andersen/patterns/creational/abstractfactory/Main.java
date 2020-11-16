package com.andersen.patterns.creational.abstractfactory;

public class Main {
    public static void main(String[] args) {
        DeviceFactory deviceFactory = getDeviceFactory(Language.RU);

        Keyboard keyboard = deviceFactory.getKeyboard();
        Mouse mouse = deviceFactory.getMouse();
        Touchpad touchpad = deviceFactory.getTouchpad();

        keyboard.print();
        mouse.click();
        touchpad.track(10, 20);
    }

    public static DeviceFactory getDeviceFactory(Language lang) {
        switch (lang) {
            case RU:
                return new RuDeviceFactory();
            case EN:
                return new EnDeviceFactory();
            default:
                throw new IllegalArgumentException();
        }
    }
}
