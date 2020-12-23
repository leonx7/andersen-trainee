package com.andersen.shop.service;

import com.andersen.shop.dao.UserDao;
import com.andersen.shop.exeptions.UserNotFoundException;
import com.andersen.shop.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public boolean login(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        return userDao.validate(username, password);
    }

    public User getUser(String username) throws UserNotFoundException {
        User user = userDao.getUser(username);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("User not found!");
        }
        return user;
    }

    public void addUser(HttpServletRequest req) {
        String username = req.getParameter("username");
        String encodedPassword = passwordEncoder.encode(req.getParameter("password"));
        if (!username.equals("") && !encodedPassword.equals("")) {
            userDao.addUser(username, encodedPassword);
        } else {
            throw new RuntimeException("Username or password can't be empty!");
        }
    }
}
