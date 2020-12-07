package com.andersen.shop.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Basket {
    private Map<Integer, Integer> products = new HashMap<>();

    private static final Logger logger = LogManager.getLogger(Basket.class);

    public void add(Integer id, Integer quantity) {
        products.put(id, quantity);
        logger.info("Product with id " + id + " added to basket");
    }

    public void delete(Integer id) {
        products.remove(id);
        logger.info("Product with id " + id + " removed from basket");
    }

    public void clear() {
        products.clear();
        System.out.println("Your basket was cleaned");
        logger.info("The basket was cleaned");
    }

    public void display(Catalog catalog) {
        if (products.isEmpty()) {
            System.out.println("Your basket is empty");
        } else {
            for (Integer integer : products.keySet()) {
                Product product = catalog.getProduct(integer);
                System.out.println(product.getName() + ", qty: " + products.get(integer));
            }
        }
    }
}
