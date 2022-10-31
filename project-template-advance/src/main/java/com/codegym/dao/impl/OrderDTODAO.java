package com.codegym.dao.impl;

import com.codegym.dao.IOrderDAO;
import com.codegym.dao.IOrderDTODAO;
import com.codegym.model.Order;
import com.codegym.model.dto.OrderItemDTO;
import com.codegym.dao.DatabaseQuery;
import com.codegym.dao.IOrderItemDTODAO;
import com.codegym.model.dto.OrderDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDTODAO extends DatabaseQuery implements IOrderDTODAO {
    private static final String UPDATE_ORDER_BYORDERID = "UPDATE `order` SET `idstatus` = ? WHERE (`id` = ?);";
    private IOrderDAO iOrderDAO;
    private IOrderItemDTODAO iOrderItemDTODAO;

    public OrderDTODAO() {
        this.iOrderDAO = new OrderDAO();
        this.iOrderItemDTODAO = new OrderItemDTODAO();
    }
    @Override
    public void insertOrderDTO(OrderDTO orderDTO) throws SQLException {

    }

    @Override
    public OrderDTO selectOrderDTO(int id) throws SQLException {

        Order order = iOrderDAO.selectOrder(id);
        List<OrderItemDTO> orderItemList = iOrderItemDTODAO.selectAllOrderItemDTOByOrderId(order.getId());
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderToOrderDTO(order);
        orderDTO.setOrderItemList(orderItemList);

        return orderDTO;
    }

    @Override
    public List<OrderDTO> selectAllOrderDTO() throws SQLException {
        List<Order> orderList = iOrderDAO.selectAllOrders();
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for(Order order : orderList){
            List<OrderItemDTO> orderItemList = iOrderItemDTODAO.selectAllOrderItemDTOByOrderId(order.getId());
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setOrderToOrderDTO(order);
            orderDTO.setOrderItemList(orderItemList);

            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }

    @Override
    public boolean deleteOrderDTO(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateOrderDTO(OrderDTO orderDTO) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_BYORDERID);
        preparedStatement.setInt(1, orderDTO.getIdStatus());
        preparedStatement.setInt(2, orderDTO.getId());

        int row = preparedStatement.executeUpdate();

        return row >=1 ;
    }

    @Override
    public boolean updateOrderDTOWithSP(OrderDTO orderDTO) throws SQLException {
        return false;
    }

    @Override
    public boolean checkEmailExists(String email) throws SQLException {
        return false;
    }
}
