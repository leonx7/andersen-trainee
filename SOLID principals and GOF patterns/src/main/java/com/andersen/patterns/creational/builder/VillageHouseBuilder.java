package com.andersen.patterns.creational.builder;

public class VillageHouseBuilder extends HouseBuilder {

    @Override
    void setAddress() {
        house.setAddress("Village");
    }

    @Override
    void setNumFloors() {
        house.setNumFloors(2);
    }

    @Override
    void setNumFlats() {
        house.setNumFlats(4);
    }
}
