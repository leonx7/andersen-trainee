package com.andersen.patterns.creational.factorymethod;

public class RomanWatchMaker implements WatchMaker {
    @Override
    public Watch createWatch() {
        return new RomanWatch();
    }
}
