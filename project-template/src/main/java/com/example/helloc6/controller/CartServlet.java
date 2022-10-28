package com.example.helloc6.controller;

import com.example.helloc6.model.Order;
import com.example.helloc6.model.dto.OrderDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="CartServlet", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "checkout":
                showCartCheckoutPage(req, resp);
                break;
            default:
                showCartPage(req, resp);

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "order":
                processOrder(req, resp);
                break;
            default:
        }
    }

    private void processOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Gson gson = new Gson();
        OrderDTO orderDTO = gson.fromJson(req.getReader(), OrderDTO.class);
        System.out.println(orderDTO);

        orderDTO.setName("Quang CODEGYM");
        String objRespone = gson.toJson(orderDTO);
        PrintWriter printWriter = resp.getWriter();
        printWriter.println(objRespone);
    }

    private void showCartCheckoutPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/frontend/checkout.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showCartPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/frontend/cart.jsp");
        requestDispatcher.forward(req, resp);
    }
}
