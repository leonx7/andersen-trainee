package com.andersen.shop.controller;

import com.andersen.shop.service.ProductService;
import com.andersen.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ShopController {
    private final UserService userService;
    private final ProductService productService;

    public ShopController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/shop")
    public String getShop(HttpServletRequest req, Model model) {
        int userId = userService.getIdFromCookie(req);
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("productsFromBasket", productService.getProductsFromBasket(userId));
        model.addAttribute("userId", userId);
        return "shop";
    }

    @PostMapping("shop/add")
    public String addProduct(HttpServletRequest req) {
        int userId = userService.getIdFromCookie(req);
        productService.addToBasket(req, userId);
        return "redirect: /shop";
    }

    @PostMapping("shop/delete")
    public String deleteProduct(HttpServletRequest req) {
        int userId = userService.getIdFromCookie(req);
        productService.deleteFromBasket(req, userId);
        return "redirect: /shop";
    }
}
