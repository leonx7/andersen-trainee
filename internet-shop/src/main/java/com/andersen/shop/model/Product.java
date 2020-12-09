package com.andersen.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    private String name;
    private ProductGroup productGroup;
    private double price;
    private Currency currency;

    @Override
    public String toString() {
        return String.format("%-4s %-10s %-6s %-3s", id, name, price, "rub");
    }
}
