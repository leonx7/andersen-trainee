package com.andersen.shop.servlets;

import com.andersen.shop.service.ProductService;
import com.andersen.shop.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class MainServlet extends HttpServlet {
    private ProductService productService = new ProductService();
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action) {
            case "/register":
                renderRegisterPage(req, resp);
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
        String action = req.getServletPath();
        switch (action) {
            case "/login":
                if (userService.login(req)) {
                    userService.createCookie(req,resp);
                    resp.sendRedirect(req.getContextPath() + "/products");
                } else
                    throw new RuntimeException("Invalid username or password");
                break;
            case "/register":
                userService.addUser(req);
                resp.sendRedirect(req.getContextPath() + "/login");
                break;
            case "/products":
                int userID = getIdFromOptional(readCookie(req));
                productService.addToBasket(req, userID);
                resp.sendRedirect(req.getContextPath() + "/products");
                break;
        }
    }

    private void renderShopPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = getIdFromOptional(readCookie(req));
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

    private void renderRegisterPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
        dispatcher.forward(req, resp);
    }

    private Optional<String> readCookie(HttpServletRequest req) {
        return Arrays.stream(req.getCookies())
                .filter(c -> "userId".equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
    }

    private int getIdFromOptional(Optional<String> o){
        int id = 0;
        if(o.isPresent()){
            id = Integer.parseInt(o.get());
        }
        return id;
    }
}
