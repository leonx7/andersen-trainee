package com.andersen.shop.dao;

import com.andersen.shop.ConnectionFactory;
import com.andersen.shop.dto.ProductDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public List<ProductDto> getAllProducts() {
        List<ProductDto> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ProductDto product = new ProductDto();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
