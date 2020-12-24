package com.andersen.shop.service;

import com.andersen.shop.dao.BasketDao;
import com.andersen.shop.dao.ProductDAO;
import com.andersen.shop.model.Item;
import com.andersen.shop.model.Product;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
public class ProductService {
    private final ProductDAO productDAO;
    private final BasketDao basketDao;

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public List<Item> getItemsFromBasket(Principal principal) {
        String username = principal.getName();
        return basketDao.getAllItems(username);
    }

    public int addToBasket(HttpServletRequest req, Principal principal) {
        long productId = Long.parseLong(req.getParameter("productId"));
        double price = Double.parseDouble(req.getParameter("price"));
        String name = req.getParameter("name");
        String username = principal.getName();
        return basketDao.addToBasket(username, productId, name, price);
    }

    public int deleteFromBasket(HttpServletRequest req, Principal principal) {
        long productId = Long.parseLong(req.getParameter("productId"));
        String username = principal.getName();
        return basketDao.deleteFromBasket(username, productId);
    }
}
