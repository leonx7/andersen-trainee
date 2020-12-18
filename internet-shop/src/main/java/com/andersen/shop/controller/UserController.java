package com.andersen.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/internet-shop")
    public String showStartPage() {
        return "index";
    }
}
