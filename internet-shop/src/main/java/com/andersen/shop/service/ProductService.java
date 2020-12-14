package com.andersen.shop.service;

import com.andersen.shop.dao.ProductDAO;
import com.andersen.shop.model.Product;

import java.util.List;

public class ProductService {
    private ProductDAO productDAO = new ProductDAO();

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }
}
