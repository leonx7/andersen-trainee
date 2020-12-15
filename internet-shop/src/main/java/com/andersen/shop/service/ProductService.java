package com.andersen.shop.service;

import com.andersen.shop.dao.ProductDAO;
import dto.ProductDto;

import java.util.List;

public class ProductService {
    private ProductDAO productDAO = new ProductDAO();

    public List<ProductDto> getAllProducts() {
        return productDAO.getAllProducts();
    }
}
