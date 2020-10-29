package com.andersen.patterns.creational.builder;

public abstract class HouseBuilder {
    House house;

    public void createHouse() {
        house = new House();
    }

    abstract void setAddress();

    abstract void setNumFloors();

    abstract void setNumFlats();

    public House getHouse() {
        return house;
    }
}
