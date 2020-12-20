package com.andersen.shop.mapper;

import com.andersen.shop.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    public static String BASE_SQL = "SELECT u.id, u.username, u.password FROM users u";

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong("id");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        return new User(id, username, password);
    }
}
