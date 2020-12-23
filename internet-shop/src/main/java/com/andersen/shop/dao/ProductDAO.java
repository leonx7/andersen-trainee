package com.andersen.shop.dao;

import com.andersen.shop.mapper.ProductMapper;
import com.andersen.shop.model.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Transactional
public class ProductDAO extends JdbcDaoSupport {

    public ProductDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Product> getAllProducts() {
        String sql = ProductMapper.BASE_SQL;
        return this.getJdbcTemplate().query(sql, new BeanPropertyRowMapper<>(Product.class));
    }

    public Product getProductById(long id) {
        String sql = ProductMapper.BASE_SQL + " WHERE p.id = ?";
        return this.getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper<>(Product.class), id);
    }
}
