package com.andersen.shop.dao;

import com.andersen.shop.exeptions.UserNotFoundException;
import com.andersen.shop.mapper.UserMapper;
import com.andersen.shop.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Types;

@Transactional
public class UserDao extends JdbcDaoSupport {

    public UserDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public User getUser(String username) {
        String sql = UserMapper.BASE_SQL + " WHERE u.username = ?";
        assert this.getJdbcTemplate() != null;
        return this.getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
    }

    public boolean addUser(String username, String password) {
        String sqlInsert = "INSERT INTO users (username, password) VALUES (?, ?)";
        if (!usernameExists(username)) {
            int i = this.getJdbcTemplate().update(sqlInsert, username, password);
            return i == 1;
        }
        return false;
    }

    public boolean validate(String username, String password) {
        String sql = UserMapper.BASE_SQL + " WHERE u.username = ? AND u.password = ?";
        try {
            this.getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username, password);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public long getUserID(String username) throws UserNotFoundException {
        String sql = UserMapper.BASE_SQL + " WHERE u.username = ?";
        try {
            User user = this.getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
            return user.getId();
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        throw new UserNotFoundException("The user with name " + username + " does not exist!");
    }

    private boolean usernameExists(String username) {
        String sql = UserMapper.BASE_SQL + " WHERE u.username = ?";
        try {
            this.getJdbcTemplate().queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
