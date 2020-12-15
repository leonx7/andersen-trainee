package com.andersen.shop.dao;

import com.andersen.shop.ConnectionFactory;
import dto.UserDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public boolean addUser(UserDto user) {
        String sql = "INSERT INTO internet_shop.users (name, password) VALUES (?, ?)";

        try (PreparedStatement statement = ConnectionFactory.getConnection().prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            int i = statement.executeUpdate();
            if (i == 1)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

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
