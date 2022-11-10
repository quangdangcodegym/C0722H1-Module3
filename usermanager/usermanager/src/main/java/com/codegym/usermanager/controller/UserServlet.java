package com.codegym.usermanager.controller;

import com.codegym.usermanager.dao.CountryDAO;
import com.codegym.usermanager.dao.ICountryDAO;
import com.codegym.usermanager.dao.IUserDAO;
import com.codegym.usermanager.dao.UserDAO;
import com.codegym.usermanager.model.Country;
import com.codegym.usermanager.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {

    private IUserDAO userDAO;
    private ICountryDAO countryDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAO();
        countryDAO = new CountryDAO();

        // applicationScope <=> getServletContext()
        if (getServletContext().getAttribute("listCountry") == null) {
            getServletContext().setAttribute("listCountry", countryDAO.selectAllCountrys());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            default:
                showListUser(req, resp);

        }
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User u = userDAO.selectUser(id);

        req.setAttribute("user", u);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/user/edit.jsp");
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                insertUser(req, resp);
                break;
            case "edit":
                updateUser(req, resp);
                break;
            default:

        }
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        int idCountry =Integer.parseInt( req.getParameter("idcountry"));

        User user = new User(id, name, email, idCountry);
        userDAO.updateUser(user);

        resp.sendRedirect("/user");

    }

    private void insertUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        int idCountry = Integer.parseInt(req.getParameter("idcountry"));
        User user = new User(-1, name, email, idCountry);
        userDAO.insertUser(user);

        req.setAttribute("message", "Insert success!!");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/user/create.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showListUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        List<User> list = userDAO.selectAllUsers();


        List<Country> listCountry = countryDAO.selectAllCountrys();

        req.setAttribute("listUser", list);
        req.setAttribute("listCountry", listCountry);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/user/list.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/user/create.jsp");
        requestDispatcher.forward(req, resp);
    }
}
