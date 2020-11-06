package com.andersen.patterns.structural.adapter;

public class AdapterApp {
    public static void main(String[] args) {
        VectorGraphicsInterface vgi1 = new VectorAdapterFromRaster();
        vgi1.drawLine();
        vgi1.drawSquare();

        VectorGraphicsInterface vgi2 = new VectorAdapterFromRaster2();
        vgi2.drawLine();
        vgi2.drawSquare();
    }
}
