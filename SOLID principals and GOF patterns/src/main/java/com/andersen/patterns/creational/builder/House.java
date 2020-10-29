package com.andersen.patterns.creational.builder;

public class House {
    private String address;
    private int numFloors;
    private int numFlats;

    public House() {
    }

    public House(String address, int numFloors, int numFlats) {
        this.address = address;
        this.numFloors = numFloors;
        this.numFlats = numFlats;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumFloors(int numFloors) {
        this.numFloors = numFloors;
    }

    public void setNumFlats(int numFlats) {
        this.numFlats = numFlats;
    }

    @Override
    public String toString() {
        return "House{" +
                "address='" + address + '\'' +
                ", numFloors=" + numFloors +
                ", numFlats=" + numFlats +
                '}';
    }
}
