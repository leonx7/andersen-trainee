package com.andersen.shop.controller;

import com.andersen.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    private final UserService userService = new UserService();

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest req, HttpServletResponse resp) {
        if (userService.login(req)) {
            userService.createCookie(req, resp);
        } else
            throw new RuntimeException("Invalid username or password");
        return "redirect: /shop";
    }

    @GetMapping("/registration")
    public String getRegistration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String register(HttpServletRequest req) {
        userService.addUser(req);
        return "redirect: /login";
    }
}
