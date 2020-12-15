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
    private ProductService productService = new ProductService();
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action) {
            case "/register":
                renderRegisterPage(req, resp);
                break;
            default:
                renderLoginPage(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/login":
                if (userService.login(req)) {
                    renderShopPage(req, resp);
                } else
                    throw new RuntimeException("Invalid username or password");
                break;
            case "/register":
                userService.addUser(req);
                resp.sendRedirect(req.getContextPath() + "/login");
                break;
        }
    }

    private void renderShopPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", productService.getAllProducts());
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


}
