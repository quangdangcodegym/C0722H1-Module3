package com.example.helloc6.controller;

import com.example.helloc6.dao.impl.CountryDAO;
import com.example.helloc6.dao.ICountryDAO;
import com.example.helloc6.dao.IUserDAO;
import com.example.helloc6.dao.impl.UserDAO;
import com.example.helloc6.model.Country;
import com.example.helloc6.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {

    private IUserDAO iUserDAO;
    private ICountryDAO iCountryDAO;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                default:
                    listUser(request, response);

            }
        }catch (SQLException sql){

        }
        catch (IOException ex) {
            throw new ServletException(ex);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        try{
            int id = Integer.parseInt(request.getParameter("id"));
            User user = iUserDAO.selectUser(id);
            RequestDispatcher requestDispatcher;
            if(user ==null){
                // User khong ton tai
                requestDispatcher = request.getRequestDispatcher("/WEB-INF/admin/user/list.jsp");
                request.setAttribute("message", "User khong ton tai");
                List<User> list = iUserDAO.selectAllUsers();
                request.setAttribute("list", list);

            }else{
                requestDispatcher = request.getRequestDispatcher("/WEB-INF/admin/user/edit.jsp");
                request.setAttribute("user", user);
            }

            requestDispatcher.forward(request, response);


        }catch (NumberFormatException ex){

        }


    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int page = 1;
        int recordsPerPage = 3;
        String q = "";
        int idcountry = -1;

        if(request.getParameter("q")!=null){
            q = request.getParameter("q");
        }
        if(request.getParameter("idcountry")!=null){
            idcountry = Integer.parseInt(request.getParameter("idcountry"));
        }
        List<User> userList = iUserDAO.selectAllUsersPaggingFilter((page-1)*recordsPerPage,recordsPerPage, q, idcountry);
        int noOfRecords = iUserDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));

        request.setAttribute("listUser", userList);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);

        request.setAttribute("q", q);
        request.setAttribute("idcountry", idcountry);




        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/admin/user/list.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin/user/create.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String action  = request.getParameter("action");
            if(action==null){
                action="";
            }
            try{
                switch (action){
                    case "create":
                        insertUser(request, response);
                        break;
                    case "edit":
                        editUser(request, response);
                        break;
                }
            }catch (SQLException ex){
                ex.printStackTrace();
            }


    }

    private void editUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<String> errors = new ArrayList<>();

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/admin/user/edit.jsp");
        User user = new User();
        try{
            int idUser = Integer.parseInt(request.getParameter("id"));

            String email = request.getParameter("email");
            String name = request.getParameter("name");
            int idCountry = Integer.parseInt(request.getParameter("country"));

            user = iUserDAO.selectUser(idUser);
            boolean checkEmail  = false;
            if(user.getEmail().equals(email)){
                checkEmail = true;
            }
            user.setName(name);
            user.setEmail(email);
            user.setIdCountry(idCountry);

            ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
            Validator validator = validatorFactory.getValidator();
            Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
            if(!constraintViolations.isEmpty()){
                for(ConstraintViolation<User> constraintViolation : constraintViolations){
                    errors.add(constraintViolation.getMessage());
                }
                request.setAttribute("user", user);
                request.setAttribute("errors", errors);
            }else{

                Country country = iCountryDAO.selectCountry(idCountry);
                boolean isEmailValid = (checkEmail==true || !iUserDAO.checkEmailExists(user.getEmail()));
                if(isEmailValid){
                    if(country==null){
                        errors.add("Mã country không hợp lệ");
                        request.setAttribute("errors", errors);
                    }else{
                        iUserDAO.updateUserWithSP(user);
                        request.setAttribute("message", "Update success!!.....");
                        List<User> listUser = iUserDAO.selectAllUsers();
                        request.setAttribute("list", listUser);
                        requestDispatcher = request.getRequestDispatcher("/WEB-INF/admin/user/list.jsp");
                    }
                }else {
                    if(country==null){
                        errors.add("Mã country không hợp lệ");
                        request.setAttribute("errors", errors);
                    }
                    request.setAttribute("user", user);
                    errors.add("Email đã tồn tại");
                    request.setAttribute("errors", errors);
                }

            }
            requestDispatcher.forward(request, response);
        }catch (NumberFormatException numberFormatException){
            //
            errors.add("Định dạng của country không hợp lệ");
            request.setAttribute("errors", errors);
            request.setAttribute("user", user);
            requestDispatcher.forward(request, response);
        }




    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<String> errors = new ArrayList<>();
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/admin/user/create.jsp");
        User user = new User();
            try{

                user.setEmail(email);
                user.setName(name);
                int idCountry = Integer.parseInt(request.getParameter("country"));
                // Kiểm tra idCountry có hợp lệ hay koong
                user.setIdCountry(idCountry);
                ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
                Validator validator = validatorFactory.getValidator();
                Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

                if(!constraintViolations.isEmpty()){
                    // Lỗi ràng buộc
                    for(ConstraintViolation<User> constraintViolation : constraintViolations){
                        errors.add(constraintViolation.getMessage());
                    }
                    request.setAttribute("user", user);
                    request.setAttribute("errors", errors);
//                errors.isEmpty()
                }else{
                    // Không có lỗi
                    // Kiểm tra email có tồn tại hay chưa
                    if(iUserDAO.checkEmailExists(email)){
                        //Da ton tai email
                        request.setAttribute("user", user);
                        errors.add("Email đã tồn tại");
                    }else{
                        iUserDAO.insertUser(user);
                        request.setAttribute("message", "Insert success!!.....");
                    }

                }
                requestDispatcher.forward(request, response);
            }catch (NumberFormatException numberFormatException){
                //
                errors.add("Định dạng của country không hợp lệ");
                request.setAttribute("errors", errors);
                request.setAttribute("user", user);
                requestDispatcher.forward(request, response);
            }


    }

    @Override
    public void init() throws ServletException {
        iUserDAO = new UserDAO();
        iCountryDAO = new CountryDAO();

        List<Country> listCountry = iCountryDAO.selectAllCountry();

        if(this.getServletContext().getAttribute("listCountry")==null){
            this.getServletContext().setAttribute("listCountry", listCountry);
        }

    }
}
