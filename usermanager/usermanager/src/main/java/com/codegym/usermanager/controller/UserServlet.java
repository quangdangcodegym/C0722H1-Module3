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
import javax.validation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
                listUserPaging(req,resp);

        }
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        User u = userDAO.selectUser(id);

        req.setAttribute("user", u);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/user/edit.jsp");
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

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        List<String> errors = new ArrayList<>();
        User user = new User();
        try {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            int idCountry = Integer.parseInt(req.getParameter("idcountry"));
            int id = Integer.parseInt(req.getParameter("id"));
            user.setName(name);
            user.setEmail(email);
            user.setIdCountry(idCountry);
            user.setId(id);

            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
            if (!constraintViolations.isEmpty()) {
                for (ConstraintViolation<User> e : constraintViolations) {
                    errors.add(e.getMessage());
                }
            } else {
                if (countryDAO.selectCountry(idCountry)==null) {
                    errors.add("Country not exists");
                }else{
                    if (userDAO.findUserByEmail(email) != null) {
                        errors.add("Email exists");
                    } else {
                        userDAO.updateUser(user);
//                        req.setAttribute("message", "Insert success!!");
                        resp.sendRedirect("/user");
                        return;
                    }
                }

            }
        } catch (NumberFormatException numberFormatException) {
            errors.add("Country not valid");
        }
        req.setAttribute("errors", errors);
        req.setAttribute("user", user);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/user/edit.jsp");
        requestDispatcher.forward(req, resp);






    }

    private void insertUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        User user = new User();
        try {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            int idCountry = Integer.parseInt(req.getParameter("idcountry"));
            user.setName(name);
            user.setEmail(email);
            user.setIdCountry(idCountry);

            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
            if (!constraintViolations.isEmpty()) {
                for (ConstraintViolation<User> e : constraintViolations) {
                    errors.add(e.getMessage());
                }
            } else {
                if (countryDAO.selectCountry(idCountry)==null) {
                    errors.add("Country not exists");
                }else{
                    if (userDAO.findUserByEmail(email) != null) {
                        errors.add("Email exists");
                    } else {
                        userDAO.insertUser(user);
                        req.setAttribute("message", "Insert success!!");
                    }
                }

            }
        } catch (NumberFormatException numberFormatException) {
            errors.add("Country not valid");
        }

//        errors.
        req.setAttribute("errors", errors);
        req.setAttribute("user", user);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/user/create.jsp");
        requestDispatcher.forward(req, resp);

    }
    private void listUserPaging(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int recordPerPage = 3;
        String q = "";
        int idCountry = -1;
        if (req.getParameter("q")!=null){
            q = req.getParameter("q");
        }
        if (req.getParameter("idcountry")!=null){
            idCountry = Integer.parseInt(req.getParameter("idcountry"));
        }
        if (req.getParameter("page")!=null){
            page = Integer.parseInt(req.getParameter("page"));
        }
        List<User> listUser = userDAO.selectAllUsers(q,idCountry,(page-1)*recordPerPage,recordPerPage);
        int numberOfRecord = userDAO.getNumOfRecords();
        int numberOfPage =(int) Math.ceil(numberOfRecord * 1.0/ recordPerPage);
        req.setAttribute("listUser", listUser);
        req.setAttribute("noOfPages", numberOfPage);
        req.setAttribute("currentPage", page);
        req.setAttribute("q", q);
        req.setAttribute("idcountry", idCountry);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/user/list.jsp");
        requestDispatcher.forward(req,resp);


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
