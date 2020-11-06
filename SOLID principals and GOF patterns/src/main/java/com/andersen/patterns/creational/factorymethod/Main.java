package com.andersen.patterns.creational.factorymethod;

public class Main {
    public static void main(String[] args) {
        WatchMaker maker = getMakerByName("Digital");
        Watch watch = maker.createWatch();
        watch.showTime();
    }

    public static WatchMaker getMakerByName(String maker) {
        if (maker.equals("Digital")) {
            return new DigitalWatchMaker();
        } else if (maker.equals("Roman")) {
            return new RomanWatchMaker();
        }
        throw new RuntimeException("This maker is not supported: " + maker);
    }
}
