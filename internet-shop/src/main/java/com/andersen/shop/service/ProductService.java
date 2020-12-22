package com.andersen.shop.service;

import com.andersen.shop.dao.BasketDao;
import com.andersen.shop.dao.ProductDAO;
import com.andersen.shop.model.Product;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class ProductService {
    private final ProductDAO productDAO;
    private final BasketDao basketDao;

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public List<Product> getProductsFromBasket(Principal principal) {
        String username = new String();
        if (Objects.nonNull(principal)) {
            username = principal.getName();
        }
        return basketDao.getAllProducts(username);
    }

    public int addToBasket(HttpServletRequest req, Principal principal) {
        long productId = Long.parseLong(req.getParameter("productId"));
        String username = new String();
        if (Objects.nonNull(principal)) {
            username = principal.getName();
        }
        return basketDao.addToBasket(username, productId);
    }

    public int deleteFromBasket(HttpServletRequest req, Principal principal) {
        long productId = Long.parseLong(req.getParameter("productId"));
        String username = new String();
        if (Objects.nonNull(principal)) {
            username = principal.getName();
        }
        return basketDao.deleteFromBasket(username, productId);
    }
}
