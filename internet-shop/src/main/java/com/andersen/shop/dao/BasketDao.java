package com.andersen.shop.dao;

import com.andersen.shop.exeptions.UserNotFoundException;
import com.andersen.shop.model.Item;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import java.util.List;

@Transactional
public class BasketDao extends JdbcDaoSupport {
    private final ProductDAO productDAO;
    private final UserDao userDao;
    @PersistenceContext()
    private EntityManager em;

    public BasketDao(ProductDAO productDAO, DataSource dataSource, UserDao userDao) {
        this.productDAO = productDAO;
        this.userDao = userDao;
        this.setDataSource(dataSource);
    }

    public List<Item> getAllItems(String username) {
        long userId = 0;
        try {
            userId = userDao.getUserID(username);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Item> cq = cb.createQuery(Item.class);
        Root<Item> item = cq.from(Item.class);
        Predicate basketIdPredicate = cb.equal(item.get("basket"), userId);
        cq.where(basketIdPredicate);
        TypedQuery<Item> query = em.createQuery(cq);
        return query.getResultList();
    }

    public int addToBasket(String username, long productId, String name, double price) {
        String sql = "INSERT INTO items (basket_id, name, product_id, quantity, price) VALUES (?, ?,  ?, ?, ?)";
        long basketId = 0;
        try {
            basketId = userDao.getUserID(username);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        if (!checkIfProductIsInTheBasket(basketId, productId)) {
            return this.getJdbcTemplate().update(sql, basketId, name, productId, 0, price);
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
        return list.contains(productId);
    }
}
