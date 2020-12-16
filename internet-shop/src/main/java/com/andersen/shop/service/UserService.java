package com.andersen.shop.service;

import com.andersen.shop.dao.UserDao;
import com.andersen.shop.dto.UserDto;

import javax.servlet.http.HttpServletRequest;

public class UserService {
    private UserDao userDao = new UserDao();

    public boolean login(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        return userDao.validate(new UserDto(username, password));
    }

    public void addUser(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        userDao.addUser(new UserDto(username, password));
    }

    public int getUserID(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        return userDao.getUserID(new UserDto(username, password));
    }
}
