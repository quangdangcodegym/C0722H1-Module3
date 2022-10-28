package com.example.helloc6.dao;


import com.example.helloc6.model.dto.OrderDTO;

import java.sql.SQLException;
import java.util.List;

public interface IOrderDTODAO {
    public void insertOrderDTO(OrderDTO orderDTO) throws SQLException;

    public OrderDTO selectOrderDTO(int id) throws SQLException;

    public List<OrderDTO> selectAllOrderDTO() throws SQLException;

    public boolean deleteOrderDTO(int id) throws SQLException;

    public boolean updateOrderDTO(OrderDTO orderDTO) throws SQLException;

    public boolean updateOrderDTOWithSP(OrderDTO orderDTO) throws SQLException;

    public boolean checkEmailExists(String email) throws SQLException;
}
