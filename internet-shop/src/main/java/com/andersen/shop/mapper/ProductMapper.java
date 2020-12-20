package com.andersen.shop.mapper;

import com.andersen.shop.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    public static String BASE_SQL = "SELECT p.id, p.name, p.price FROM products p";

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        double price = resultSet.getDouble("price");
        return new Product(id, name, price);
    }
}
