package com.andersen.shop;

import com.andersen.shop.model.Catalog;
import com.andersen.shop.model.Currency;
import com.andersen.shop.model.Product;
import com.andersen.shop.model.ProductGroup;
import com.andersen.shop.model.ProductWithExpiryDate;

import java.io.IOException;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws IOException{
        Currency byn = new Currency("Belorussian ruble", "BYN", 0.4);
        Currency usd = new Currency("US dollar", "USD", 2.5);

        Catalog catalog = new Catalog();
        catalog.add(1, new ProductWithExpiryDate(1, "Milk", ProductGroup.Food, 2.0, byn));
        catalog.add(2, new ProductWithExpiryDate(2, "Bread", ProductGroup.Food, 4.0, byn));
        catalog.add(3, new Product(3, "Knife", ProductGroup.NotFood, 35.0, usd));
        catalog.add(4, new Product(4, "Mirror", ProductGroup.NotFood, 20.0, usd));

        ProgramEngine programEngine = new ProgramEngine();
        programEngine.run(catalog);
    }
}