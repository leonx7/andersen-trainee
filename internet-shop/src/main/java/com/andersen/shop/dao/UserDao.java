package com.andersen.shop.dao;

import com.andersen.shop.ConnectionFactory;
import dto.UserDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public boolean validate(UserDto user) {
        boolean status = false;

        String sql = "SELECT * FROM users WHERE name = ? and password = ?";
        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            ResultSet resultSet = statement.executeQuery();
            status = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
