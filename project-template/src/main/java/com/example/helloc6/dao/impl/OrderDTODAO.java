package com.example.helloc6.dao.impl;

import com.example.helloc6.dao.DatabaseQuery;
import com.example.helloc6.dao.IOrderDAO;
import com.example.helloc6.dao.IOrderDTODAO;
import com.example.helloc6.dao.IOrderItemDTODAO;
import com.example.helloc6.model.Order;
import com.example.helloc6.model.dto.OrderDTO;
import com.example.helloc6.model.dto.OrderItemDTO;

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
