package com.andersen.shop.dao;

import com.andersen.shop.ConnectionFactory;
import dto.ProductDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasketDao {
    static private final Logger logger = LogManager.getLogger(ConnectionFactory.class);

    public List<ProductDto> getAllProducts() {
        List<ProductDto> products = new ArrayList<>();
        String sql = "SELECT * FROM products_in_basket WHERE basket_id";
        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ProductDto product = new ProductDto();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setQuantity(resultSet.getInt("price"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("Selected all products from 'basket'");
        return products;
    }
}
