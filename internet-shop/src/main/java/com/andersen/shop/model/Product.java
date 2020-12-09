package com.andersen.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private ProductGroup productGroup;
    private double price;

    @Override
    public String toString() {
        return String.format("%-4s %-10s %-6s %-3s", id, name, price, "rub");
    }
}
