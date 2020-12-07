package com.andersen.shop;

import com.andersen.shop.model.Catalog;
import com.andersen.shop.model.Product;
import com.andersen.shop.model.ProductGroup;

public class App {
    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        catalog.add(1, new Product(1, "Milk", ProductGroup.Food, 2.0));
        catalog.add(2, new Product(2, "Bread", ProductGroup.Food, 4.0));
        catalog.add(3, new Product(3, "Knife", ProductGroup.NotFood, 35.0));
        catalog.add(4, new Product(4, "Mirror", ProductGroup.NotFood, 20.0));

        ProgramEngine.run(catalog);
    }
}