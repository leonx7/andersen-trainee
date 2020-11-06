package com.andersen.patterns.creational.builder;

public class Main {
    public static void main(String[] args) {
        Director director = new Director();
        director.setBuilder(new CityHouseBuilder());
        House house = director.buildHouse();
        System.out.println(house);
    }
}
