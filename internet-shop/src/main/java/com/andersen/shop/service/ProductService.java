package com.andersen.shop.service;

import com.andersen.shop.dao.ProductDAO;
import com.andersen.shop.dto.ProductDto;

import java.util.List;

public class ProductService {
    private ProductDAO productDAO = new ProductDAO();

    public List<ProductDto> getAllProducts() {
        return productDAO.getAllProducts();
    }
}
