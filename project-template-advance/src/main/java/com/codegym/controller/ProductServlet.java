package com.codegym.controller;

import com.codegym.dao.IProductDTODAO;
import com.codegym.model.Category;
import com.codegym.dao.ICategoryDAO;
import com.codegym.dao.impl.CategoryDAO;
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

@WebServlet(name = "ProductServlet", urlPatterns = "/product")
public class ProductServlet extends HttpServlet {


    private IProductDTODAO iProductDTODAO;
    private ICategoryDAO iCategoryDAO;
    @Override
    public void init() throws ServletException {
        iProductDTODAO = new ProductDTODAO();
        iCategoryDAO = new CategoryDAO();
        List<Category> categoryList = iCategoryDAO.selectAllCategory();
        if (getServletContext().getAttribute("categoryList") == null) {
            getServletContext().setAttribute("categoryList", categoryList);
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
                showCreateProduct(req, resp);
                break;
            default:
                showListProduct(req, resp);
        }


    }

    private void showCreateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/admin/product/create.jsp");
        requestDispatcher.forward(req, resp);
    }

    private void showListProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        int page = 1;
        int recordsPerPage = 3;
        String q = "";
        int idcategory = -1;

        if(req.getParameter("q")!=null){
            q = req.getParameter("q");
        }
        if(req.getParameter("idcategory")!=null){
            idcategory = Integer.parseInt(req.getParameter("idcategory"));
        }
        if(req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));

        List<ProductDTO> productDTOList =  iProductDTODAO.selectAllProductDTO((page-1)*recordsPerPage,recordsPerPage, q, idcategory);
        int noOfRecords = iProductDTODAO.getNoOfRecords();

        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);



        req.setAttribute("noOfPages", noOfPages);
        req.setAttribute("currentPage", page);

        req.setAttribute("q", q);
        req.setAttribute("idcategory", idcategory);

        req.setAttribute("listProductDTO", productDTOList);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/admin/product/list.jsp");
        requestDispatcher.forward(req, resp);
    }
}
