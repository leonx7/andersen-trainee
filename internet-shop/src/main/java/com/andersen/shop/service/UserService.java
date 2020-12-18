package com.andersen.shop.service;

import com.andersen.shop.dao.UserDao;
import com.andersen.shop.dto.UserDto;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

public class UserService {
    private final UserDao userDao = new UserDao();

    public boolean login(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        return userDao.validate(new UserDto(username, password));
    }

    public void addUser(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (!username.equals("") && !password.equals("")) {
            userDao.addUser(new UserDto(username, password));
        } else {
            throw new RuntimeException("Username or password can't be empty!");
        }
    }

    public int getUserID(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        return userDao.getUserID(new UserDto(username, password));
    }

    public Cookie createCookie(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Cookie credentialsCookie = new Cookie("userId", Integer.toString(userDao.getUserID(new UserDto(username, password))));
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
