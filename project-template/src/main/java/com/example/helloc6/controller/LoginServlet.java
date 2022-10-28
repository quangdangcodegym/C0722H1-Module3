package com.example.helloc6.controller;

import com.example.helloc6.dao.IUserDAO;
import com.example.helloc6.dao.impl.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet" , urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private IUserDAO iUserDAO;

    @Override
    public void init() throws ServletException {
        iUserDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/admin/user/login.jsp");
        requestDispatcher.forward(req, resp);


        System.out.println("Request session ID: " + req.getRequestedSessionId());
        System.out.println("Session ID: " + req.getSession().getId());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (iUserDAO.checkUserExists(username, password)) {
            Cookie cookieUserName = new Cookie("username", username);
            Cookie cookiePassword = new Cookie("password", password);
            cookieUserName.setMaxAge(10*60);
            cookiePassword.setMaxAge(10*60);
            resp.addCookie(cookieUserName);
            resp.addCookie(cookiePassword);

//            req.getSession().setAttribute("username", username);
//            req.getSession().setAttribute("password", password);
            resp.sendRedirect("/product");
        }else{
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/admin/user/login.jsp");
            req.setAttribute("message", "Ten tai khoan va mat khau khong dung");
            requestDispatcher.forward(req, resp);
        }

    }
}
