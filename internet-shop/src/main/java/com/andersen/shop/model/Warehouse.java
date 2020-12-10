package com.andersen.shop.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    @Getter
    private final Map<Integer, Integer> items = new HashMap<>();

    public void add(Integer productId, Integer quantity) {
        items.put(productId, quantity);
    }
}
