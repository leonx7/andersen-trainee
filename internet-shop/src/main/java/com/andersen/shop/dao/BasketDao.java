package com.andersen.shop.dao;

import com.andersen.shop.DataSourceFactory;
import com.andersen.shop.dto.ProductDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasketDao {
    private ProductDAO productDAO = new ProductDAO();
    private UserDao userDao = new UserDao();
    private DataSource ds = DataSourceFactory.getMySQLDataSource();
    static private final Logger logger = LogManager.getLogger(DataSourceFactory.class);

    public List<ProductDto> getProducts(int userID) {
        List<ProductDto> products = new ArrayList<>();
        String sql = "SELECT * FROM products_in_basket WHERE basket_id = ?";
        try (PreparedStatement statement = ds.getConnection().prepareStatement(sql)) {
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ProductDto product = productDAO.getProductById(resultSet.getInt("product_id"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("Selected all products from 'basket'");
        return products;
    }

    public boolean addProduct(ProductDto product, int userID) {
        String sql = "INSERT INTO internet_shop.products_in_basket (product_id, basket_id) VALUES (?, ?)";
        if(!checkIfProductIsInTheBasket(product,userID)){
            try (PreparedStatement statement = ds.getConnection().prepareStatement(sql)) {
                statement.setInt(1, product.getProductId());
                statement.setInt(2, userID);
                int i = statement.executeUpdate();
                if (i == 1)
                    return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean checkIfProductIsInTheBasket(ProductDto product, int userID){
        boolean isPresent = false;
        String sql = "SELECT * FROM products_in_basket WHERE basket_id = ? and product_id = ?";
        try (PreparedStatement statement = ds.getConnection().prepareStatement(sql)) {
            statement.setInt(1, product.getProductId());
            statement.setInt(2, userID);
            ResultSet resultSet = statement.executeQuery();
            isPresent = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isPresent;
    }
}
