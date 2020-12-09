package com.andersen.shop.model;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Catalog {
    @Getter
    private Map<Integer, Product> products = new HashMap<>();

    static private final Logger logger = LogManager.getLogger(Catalog.class);

    public void add(Integer productId, Product product) {
        products.put(productId, product);
        logger.info("Product with id " + product.getId() + " added to catalog");
    }

    public void delete(Integer productId) {
        products.remove(productId);
        logger.info("Product with id " + productId + " removed from catalog");
    }

    public Product getProduct(Integer productId) {
        return products.get(productId);
    }

    public void display() {
        for (Integer productId : products.keySet()) {
            System.out.println(products.get(productId));
        }
    }
}
