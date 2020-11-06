package com.andersen.patterns.creational.builder;

public class CityHouseBuilder extends HouseBuilder {

    @Override
    void setAddress() {
        house.setAddress("City");
    }

    @Override
    void setNumFloors() {
        house.setNumFloors(10);
    }

    @Override
    void setNumFlats() {
        house.setNumFlats(100);
    }
}
