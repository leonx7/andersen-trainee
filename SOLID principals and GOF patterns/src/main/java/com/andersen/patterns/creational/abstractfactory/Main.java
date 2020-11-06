package com.andersen.patterns.creational.abstractfactory;

public class Main {
    public static void main(String[] args) {
        DeviceFactory deviceFactory = getDeviceFactory("EN");

        Keyboard keyboard = deviceFactory.getKeyboard();
        Mouse mouse = deviceFactory.getMouse();
        Touchpad touchpad = deviceFactory.getTouchpad();

        keyboard.print();
        mouse.click();
        touchpad.track(10, 20);
    }

    public static DeviceFactory getDeviceFactory(String lang) {
        return switch (lang) {
            case "RU" -> new RuDeviceFactory();
            case "EN" -> new EnDeviceFactory();
            default -> throw new RuntimeException("Unsupported country code: " + lang);
        };
    }
}
