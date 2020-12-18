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

    public List<ProductDto> getProductsFromBasket(int userId) {
        return basketDao.getProducts(userId);
    }

    public boolean addToBasket(HttpServletRequest req, int userID) {
        String productId = req.getParameter("productId");
        ProductDto product = new ProductDto();
        product.setProductId(Integer.parseInt(productId));
        return basketDao.addProduct(product, userID);
    }

    public boolean deleteFromBasket(HttpServletRequest req, int userId) {
        String productId = req.getParameter("productId");
        ProductDto product = new ProductDto();
        product.setProductId(Integer.parseInt(productId));
        return basketDao.deleteProduct(product,userId);
    }
}
