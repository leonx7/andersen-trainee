package com.andersen.shop.controller;

import com.andersen.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ShopController {
    private final ProductService productService;

    @GetMapping("/shop")
    public String getShop(Model model, Principal principal) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("items", productService.getItemsFromBasket(principal));
        return "shop";
    }

    @PostMapping("shop/add")
    public String addProduct(HttpServletRequest req, Principal principal) {
        productService.addToBasket(req, principal);
        return "redirect:/shop";
    }

    @PostMapping("shop/delete")
    public String deleteProduct(HttpServletRequest req, Principal principal) {
        productService.deleteFromBasket(req, principal);
        return "redirect:/shop";
    }
}
