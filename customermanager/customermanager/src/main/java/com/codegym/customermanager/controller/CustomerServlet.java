package com.codegym.customermanager.controller;

import com.codegym.customermanager.service.CustomerService;
import com.codegym.customermanager.service.GenderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CustomerServlet",urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService;
    private GenderService genderService;


    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
        genderService = new GenderService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("listCustomer", customerService.getList());
        req.setAttribute("listGender", genderService.getList());

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/customer.jsp");
        requestDispatcher.forward(req, resp);
    }
}
