package com.andersen.shop.dao;

import com.andersen.shop.exeptions.UserNotFoundException;
import com.andersen.shop.model.Product;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class BasketDao extends JdbcDaoSupport {
    private final ProductDAO productDAO;
    private final UserDao userDao;

    public BasketDao(ProductDAO productDAO, DataSource dataSource, UserDao userDao) {
        this.productDAO = productDAO;
        this.userDao = userDao;
        this.setDataSource(dataSource);
    }

    public List<Product> getAllProducts(String username) {
        long userId = 0;
        try {
            userId = userDao.getUserID(username);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        List<Product> products = new ArrayList<>();
        String sql = "SELECT i.product_id FROM items i WHERE i.basket_id = ?";
        List<Long> list = this.getJdbcTemplate().query(sql, (resultSet, i) -> resultSet.getLong(1), userId);
        for (Long id : list) {
            products.add(productDAO.getProductById(id));
        }
        return products;
    }

    public int addToBasket(String username, long productId, double price) {
        String sql = "INSERT INTO items (basket_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        long basketId = 0;
        try {
            basketId = userDao.getUserID(username);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        if (!checkIfProductIsInTheBasket(basketId, productId)) {
            return this.getJdbcTemplate().update(sql, basketId, productId, 0, price);
        }
        return 0;
    }

    public int deleteFromBasket(String username, long productId) {
        String sql = "DELETE FROM items WHERE basket_id = ? and product_id = ?";
        long basketId = 0;
        try {
            basketId = userDao.getUserID(username);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return this.getJdbcTemplate().update(sql, basketId, productId);
    }

    public boolean checkIfProductIsInTheBasket(long basketId, long productId) {
        String sql = "SELECT i.product_id FROM items i WHERE i.basket_id = ?";
        List<Long> list = this.getJdbcTemplate().query(sql, (resultSet, i) -> resultSet.getLong(1), basketId);
        if (list.contains(productId)) {
            return true;
        }
        return false;
    }
}
