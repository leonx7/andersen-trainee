package com.andersen.shop.service;

import com.andersen.shop.dao.UserDao;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean login(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        return userDao.validate(username, password);
    }

    public void addUser(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (!username.equals("") && !password.equals("")) {
            userDao.addUser(username, password);
        } else {
            throw new RuntimeException("Username or password can't be empty!");
        }
    }

    public long getUserID(HttpServletRequest req) {
        String username = req.getParameter("username");
        return userDao.getUserID(username);
    }

    public Cookie createCookie(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Cookie credentialsCookie = new Cookie("userId", Long.toString(userDao.getUserID(username)));
        credentialsCookie.setMaxAge(60 * 60);
        credentialsCookie.setPath("http://localhost:8080/internet-shop/");
        resp.addCookie(credentialsCookie);
        return credentialsCookie;
    }

    public int getIdFromCookie(HttpServletRequest req) {
        return getIdFromOptional(readCookie(req));
    }

    private int getIdFromOptional(Optional<String> o) {
        int id = 0;
        if (o.isPresent()) {
            id = Integer.parseInt(o.get());
        }
        return id;
    }

    private Optional<String> readCookie(HttpServletRequest req) {
        return Arrays.stream(req.getCookies())
                .filter(c -> "userId".equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
    }
}
