package com.andersen.shop.dao;

import com.andersen.shop.ConnectionFactory;
import com.andersen.shop.dto.ProductDto;
import com.andersen.shop.dto.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasketDao {
    ProductDAO productDAO = new ProductDAO();
    UserDao userDao = new UserDao();
    static private final Logger logger = LogManager.getLogger(ConnectionFactory.class);

    public List<ProductDto> getProducts(UserDto user) {
        List<ProductDto> products = new ArrayList<>();
        String sql = "SELECT * FROM products_in_basket WHERE basket_id = ?";

        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            statement.setInt(1, userDao.getUserID(user));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ProductDto product = productDAO.getProductById(resultSet.getInt("product_id"));
                product.setQuantity(resultSet.getInt("quantity"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("Selected all products from 'basket'");
        return products;
    }

    public boolean addProduct(ProductDto product) {
        String sql = "INSERT INTO internet_shop.products_in_basket (product_id, basket_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            statement.setInt(1, product.getProductId());
            statement.setInt(2, product.getUserId());
            statement.setInt(3, product.getQuantity());
            int i = statement.executeUpdate();
            if (i == 1)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
