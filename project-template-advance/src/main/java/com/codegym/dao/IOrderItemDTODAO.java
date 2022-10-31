package com.codegym.dao;


import com.codegym.model.dto.OrderItemDTO;

import java.sql.SQLException;
import java.util.List;

public interface IOrderItemDTODAO {
    public void insertOrderItemDTO(OrderItemDTO orderItemDTO) throws SQLException;

    public OrderItemDTO selectOrderItemDTO(int id) throws SQLException;

    public List<OrderItemDTO> selectAllOrderItemDTOByOrderId(int idOrder) throws SQLException;

    public boolean deleteOrderItemDTO(int id) throws SQLException;

    public boolean updateOrderItemDTO(OrderItemDTO orderItemDTO) throws SQLException;

    public boolean updateOrderItemDTOWithSP(OrderItemDTO orderItemDTO) throws SQLException;

    public boolean checkEmailExists(String email) throws SQLException;
}
