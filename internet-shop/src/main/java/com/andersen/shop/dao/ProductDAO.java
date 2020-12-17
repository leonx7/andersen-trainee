package com.andersen.shop.dao;

import com.andersen.shop.DataSourceFactory;
import com.andersen.shop.dto.ProductDto;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private DataSource ds = DataSourceFactory.getMySQLDataSource();

    public List<ProductDto> getAllProducts() {
        List<ProductDto> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (PreparedStatement statement = ds.getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ProductDto product = new ProductDto();
                product.setProductId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public ProductDto getProductById(int id) {
        ProductDto product = null;
        String sql = "SELECT * FROM products WHERE id = ?";
        try (PreparedStatement statement = ds.getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            product = new ProductDto();
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getDouble("price"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}
