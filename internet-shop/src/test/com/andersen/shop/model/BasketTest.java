package com.andersen.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {
    private Basket basket;

    @BeforeEach
    public void initialize() {
        basket = new Basket();
        basket.add(1, 1);
        basket.add(2, 1);
        basket.add(3, 1);
    }

    @Test
    void add() throws NoSuchFieldException, IllegalAccessException {
        basket.add(4, 1);
        assertEquals(4, getProducts().size());
    }

    @Test
    void delete() throws NoSuchFieldException, IllegalAccessException {
        basket.delete(3);
        assertEquals(2, getProducts().size());
    }

    @Test
    void clear() throws NoSuchFieldException, IllegalAccessException {
        basket.clear();
        assertEquals(0, getProducts().size());
    }

    private Map<Integer, Integer> getProducts() throws NoSuchFieldException, IllegalAccessException {
        Field field = Basket.class.getDeclaredField("products");
        field.setAccessible(true);
        return (Map<Integer, Integer>) field.get(basket);
    }
}