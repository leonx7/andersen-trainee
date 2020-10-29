package com.andersen.patterns.creational.builder;

public class Director {
    HouseBuilder builder;

    public void setBuilder(HouseBuilder builder) {
        this.builder = builder;
    }

    House buildHouse() {
        builder.createHouse();
        builder.setAddress();
        builder.setNumFlats();
        builder.setNumFloors();
        return builder.getHouse();
    }
}
