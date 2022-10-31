package com.codegym.controller;

import com.codegym.dao.IProductDTODAO;
import com.codegym.dao.impl.ProductDTODAO;
import com.codegym.model.dto.ProductDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", urlPatterns = "")
public class HomeServlet extends HttpServlet {
    private IProductDTODAO iProductDTODAO;
    @Override
    public void init() throws ServletException {
        iProductDTODAO = new ProductDTODAO();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        System.out.println("Request session ID: " + req.getRequestedSessionId());
        System.out.println("Session ID: " + req.getSession().getId());
        List<ProductDTO> listProduct = iProductDTODAO.selectAllProductDTOs();

        req.setAttribute("listProductDTO", listProduct);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/frontend/index.jsp");
        requestDispatcher.forward(req, resp);
    }
}
