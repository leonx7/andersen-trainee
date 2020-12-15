package com.andersen.shop.service;

import com.andersen.shop.dao.UserDao;
import dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserService {
    private UserDao userDao = new UserDao();

    public boolean login(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        return userDao.validate(new UserDto(username, password));
    }
}
