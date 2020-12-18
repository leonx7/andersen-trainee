package com.andersen.shop.servlets;

import com.andersen.shop.service.ProductService;
import com.andersen.shop.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    private ProductService productService;
    private UserService userService;

    public MainServlet(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action) {
            case "/registration":
                renderRegistrationPage(req, resp);
                break;
            case "/login":
                renderLoginPage(req, resp);
                break;
            case "/products":
                renderShopPage(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int userId;
        String action = req.getServletPath();
        switch (action) {
            case "/login":
                if (userService.login(req)) {
                    userService.createCookie(req, resp);
                    resp.sendRedirect(req.getContextPath() + "/products");
                } else
                    throw new RuntimeException("Invalid username or password");
                break;
            case "/registration":
                userService.addUser(req);
                resp.sendRedirect(req.getContextPath() + "/login");
                break;
            case "/products/add":
                userId = userService.getIdFromCookie(req);
                productService.addToBasket(req, userId);
                resp.sendRedirect(req.getContextPath() + "/products");
                break;
            case "/products/delete":
                userId = userService.getIdFromCookie(req);
                productService.deleteFromBasket(req, userId);
                resp.sendRedirect(req.getContextPath() + "/products");
                break;
        }
    }

    private void renderShopPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = userService.getIdFromCookie(req);
        req.setAttribute("products", productService.getAllProducts());
        req.setAttribute("productsFromBasket", productService.getProductsFromBasket(userId));
        req.setAttribute("userId", userId);
        RequestDispatcher dispatcher = req.getRequestDispatcher("shop.jsp");
        dispatcher.forward(req, resp);
    }

    private void renderLoginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
        dispatcher.forward(req, resp);
    }

    private void renderRegistrationPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("registration.jsp");
        dispatcher.forward(req, resp);
    }
}
