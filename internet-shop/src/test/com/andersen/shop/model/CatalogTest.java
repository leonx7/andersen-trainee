package com.andersen.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CatalogTest {
    private Catalog catalog;

    @BeforeEach
    public void initialize() {
        catalog = new Catalog();
        catalog.add(1, new Product(1, "Milk", ProductGroup.Food, 2.0));
        catalog.add(2, new Product(2, "Bread", ProductGroup.Food, 4.0));
        catalog.add(3, new Product(3, "Knife", ProductGroup.NotFood, 35.0));
    }

    @Test
    void add() throws NoSuchFieldException, IllegalAccessException {
        catalog.add(4, new Product(4, "Mirror", ProductGroup.NotFood, 20.0));
        assertEquals(4, getProducts().size());
    }

    @Test
    void delete() throws NoSuchFieldException, IllegalAccessException {
        catalog.delete(3);
        assertEquals(2, getProducts().size());
    }

    @Test
    void getProduct() {
        Product product = new Product(4, "Mirror", ProductGroup.NotFood, 20.0);
        catalog.add(4, product);
        assertEquals(product, catalog.getProduct(4));
    }

    private Map<Object, Object> getProducts() throws NoSuchFieldException, IllegalAccessException {
        Field field = Catalog.class.getDeclaredField("products");
        field.setAccessible(true);
        return (Map<Object, Object>) field.get(catalog);
    }
}