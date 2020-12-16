package com.andersen.shop.service;

import com.andersen.shop.dao.BasketDao;
import com.andersen.shop.dao.ProductDAO;
import com.andersen.shop.dto.ProductDto;
import com.andersen.shop.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO = new ProductDAO();
    private BasketDao basketDao = new BasketDao();

    public List<ProductDto> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public List<ProductDto> getProductsFromBasket(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        return basketDao.getProducts(new UserDto(username, password));
    }

    public boolean addToBasket(HttpServletRequest req) {
        String userId = req.getParameter("userId");
        String productId = req.getParameter("productId");
        String quantity = req.getParameter("quantity");
        ProductDto product = new ProductDto();
        product.setUserId(Integer.parseInt(userId));
        product.setProductId(Integer.parseInt(productId));
        product.setQuantity(Integer.parseInt(quantity));
        return basketDao.addProduct(product);
    }
}
