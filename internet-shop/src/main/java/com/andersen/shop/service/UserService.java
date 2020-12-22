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

    /*public long getUserID(HttpServletRequest req) {
        String username = req.getParameter("username");
        return userDao.getUserID(username);
    }*/

   /* public Cookie createCookie(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
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
    }*/
}
