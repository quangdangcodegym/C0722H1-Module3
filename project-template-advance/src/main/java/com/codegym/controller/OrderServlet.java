package com.codegym.controller;

import com.codegym.dao.IOrderDTODAO;
import com.codegym.dao.IOrderStatusDAO;
import com.codegym.dao.impl.OrderDTODAO;
import com.codegym.dao.impl.OrderStatusDAO;
import com.codegym.model.OrderStatus;
import com.codegym.model.dto.OrderDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name="OrderServlet", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {
    private IOrderDTODAO iOrderDTODAO;
    private IOrderStatusDAO iOrderStatusDAO;

    @Override
    public void init() throws ServletException {
        iOrderDTODAO = new OrderDTODAO();
        iOrderStatusDAO = new OrderStatusDAO();
        List<OrderStatus> orderStatusList = null;
        try{
            orderStatusList = iOrderStatusDAO.selectAllOrderStatuss();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        if(getServletContext().getAttribute("orderStatusList")==null){
            getServletContext().setAttribute("orderStatusList", orderStatusList);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action){
            case "edit":
                showEditForm(req, resp);
                break;
            default:
                showListOrder(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "edit":
                editOrder(req, resp);
                break;
            default:
                showListOrder(req, resp);
        }
    }

    private void editOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            int idOrder = Integer.parseInt(req.getParameter("id"));
            int idOrderStatus = Integer.parseInt(req.getParameter("orderstatus"));
            OrderDTO orderDTO = iOrderDTODAO.selectOrderDTO(idOrder);
            orderDTO.setIdStatus(idOrderStatus);

            iOrderDTODAO.updateOrderDTO(orderDTO);
            resp.sendRedirect("/order");
        } catch (NumberFormatException numberFormatException) {

        } catch (SQLException sqlException) {

        }

    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int idOrder = -1;

        try{
            idOrder = Integer.parseInt(req.getParameter("id"));
            OrderDTO orderDTO = iOrderDTODAO.selectOrderDTO(idOrder);
            req.setAttribute("orderDTO", orderDTO);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/admin/order/edit.jsp");
            requestDispatcher.forward(req, resp);


        }catch (NumberFormatException numberFormatException){

        }catch (SQLException sqlException){

        }

    }

    private void showListOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            List<OrderDTO> orderDTOList = iOrderDTODAO.selectAllOrderDTO();
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/admin/order/list.jsp");
            req.setAttribute("orderDTOList", orderDTOList);
            requestDispatcher.forward(req, resp);
        }catch (SQLException sqlException){

        }
    }
}
