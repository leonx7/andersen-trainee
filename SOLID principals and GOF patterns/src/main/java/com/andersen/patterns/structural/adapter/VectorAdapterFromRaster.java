package com.andersen.patterns.structural.adapter;

//Adapter using inheritance
public class VectorAdapterFromRaster extends RasterGraphics implements VectorGraphicsInterface {
    @Override
    public void drawLine() {
        drawRasterLine();
    }

    @Override
    public void drawSquare() {
        drawRasterSquare();
    }
}

//Adapter using aggregation
class VectorAdapterFromRaster2 implements VectorGraphicsInterface {
    RasterGraphics rg = new RasterGraphics();

    @Override
    public void drawLine() {
        rg.drawRasterLine();
    }

    @Override
    public void drawSquare() {
        rg.drawRasterSquare();
    }
}
