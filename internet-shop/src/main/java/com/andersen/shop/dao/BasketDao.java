package com.andersen.shop.dao;

import com.andersen.shop.model.Product;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class BasketDao extends JdbcDaoSupport {
    private final ProductDAO productDAO;

    public BasketDao(ProductDAO productDAO, DataSource dataSource) {
        this.productDAO = productDAO;
        this.setDataSource(dataSource);
    }

    public List<Product> getAllProducts(long userId) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT pib.product_id FROM products_in_basket pib WHERE pib.basket_id = ?";
        List<Long> list = this.getJdbcTemplate().query(sql, new Object[]{userId}, (resultSet, i) -> resultSet.getLong(1));
        if (!list.isEmpty()) {
            for (Long id : list) {
                products.add(productDAO.getProductById(id));
            }
        }
        return products;
    }

    public int addToBasket(long basketId, long productId) {
        String sql = "INSERT INTO products_in_basket (basket_id, product_id, quantity) VALUES (?, ?, ?)";
        if (!checkIfProductIsInTheBasket(basketId, productId)) {
            return this.getJdbcTemplate().update(sql, basketId, productId, 0);
        }
        return 0;
    }

    public int deleteFromBasket(long basketId, long productId) {
        String sql = "DELETE FROM products_in_basket WHERE basket_id = ? and product_id = ?";
        return this.getJdbcTemplate().update(sql, basketId, productId);
    }

    private boolean checkIfProductIsInTheBasket(long basketId, long productId) {
        String sql = "SELECT pib.product_id FROM products_in_basket pib WHERE pib.basket_id = ?";
        List<Long> list = this.getJdbcTemplate().query(sql, new Object[]{basketId}, (resultSet, i) -> resultSet.getLong(1));
        for (Long id : list) {
            if (id == productId) {
                return true;
            }
        }
        return false;
    }

}
