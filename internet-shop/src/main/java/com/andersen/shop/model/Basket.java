package com.andersen.shop.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Basket implements Serializable {
    private int userID;
    private Map<Integer, Integer> products = new HashMap<>();

    static private final long serialVersionUID = 1L;
    static private final Logger logger = LogManager.getLogger(Basket.class);

    public void add(Integer productId, Integer quantity) {
        products.put(productId, quantity);
        logger.info("Product with id " + productId + " added to basket");
    }

    public void delete(Integer productId) {
        products.remove(productId);
        logger.info("Product with id " + productId + " removed from basket");
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
