package com.andersen.shop.service;

import com.andersen.shop.dao.BasketDao;
import com.andersen.shop.dao.ProductDAO;
import com.andersen.shop.model.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ProductService {
    private final ProductDAO productDAO;
    private final BasketDao basketDao;

    public ProductService(ProductDAO productDAO, BasketDao basketDao) {
        this.productDAO = productDAO;
        this.basketDao = basketDao;
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public List<Product> getProductsFromBasket(long userId) {
        return basketDao.getAllProducts(userId);
    }

    public int addToBasket(HttpServletRequest req, long userID) {
        long productId = Long.parseLong(req.getParameter("productId"));
        return basketDao.addToBasket(userID, productId);
    }

    public int deleteFromBasket(HttpServletRequest req, long userId) {
        long productId = Long.parseLong(req.getParameter("productId"));
        return basketDao.deleteFromBasket(userId, productId);
    }
}
