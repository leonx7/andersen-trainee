package com.andersen.shop.service;

import com.andersen.shop.dao.BasketDao;
import com.andersen.shop.dao.ProductDAO;
import com.andersen.shop.dto.ProductDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ProductService {
    private final ProductDAO productDAO = new ProductDAO();
    private final BasketDao basketDao = new BasketDao();

    public List<ProductDto> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public List<ProductDto> getProductsFromBasket(int userID) {
        return basketDao.getProducts(userID);
    }

    public boolean addToBasket(HttpServletRequest req, int userID) {
        String productId = req.getParameter("productId");
        ProductDto product = new ProductDto();
        product.setProductId(Integer.parseInt(productId));
        return basketDao.addProduct(product, userID);
    }
}
