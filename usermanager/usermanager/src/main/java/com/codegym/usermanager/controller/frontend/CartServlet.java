package com.codegym.usermanager.controller.frontend;

import com.codegym.usermanager.dao.CartDAO;
import com.codegym.usermanager.dao.ICartDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "HomeServlet", urlPatterns = "/cart")
public class CartServlet extends HttpServlet {
    private ICartDAO iCartDAO;

    @Override
    public void init() throws ServletException {
        iCartDAO = new CartDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                // dua san pham vao cart
                showAddToCart(req, resp);
                break;
            default:
                // hien thi cart

        }
    }

    private void showAddToCart(HttpServletRequest req, HttpServletResponse resp) {
        // test: http://localhost:8080/cart?action=add&idcart=4&id=1&quantity=8
        int idProduct = Integer.parseInt(req.getParameter("id"));
        int idCart = Integer.parseInt(req.getParameter("idcart"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        iCartDAO.addToCart(idCart, idProduct, quantity, 5000);

    }
}
