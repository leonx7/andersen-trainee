package com.andersen.shop.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Catalog {
    private Map<Integer, Product> products = new HashMap<>();

    private static final Logger logger = LogManager.getLogger(Catalog.class);

    public void add(Integer number, Product product) {
        products.put(number, product);
        logger.info("Product with id " + product.getId() + " added to catalog");
    }

    public void delete(Integer id) {
        products.remove(id);
        logger.info("Product with id " + id + " removed from catalog");
    }

    public Product getProduct(Integer number) {
        return products.get(number);
    }

    public void display() {
        for (Integer number : products.keySet()) {
            System.out.println(products.get(number));
        }
    }
}
