package com.example.helloc6.dao;


import com.example.helloc6.model.Order;

import java.sql.SQLException;
import java.util.List;

public interface IOrderDAO {
    public void insertOrder(Order Order) throws SQLException;

    public Order selectOrder(int id) throws SQLException;

    public List<Order> selectAllOrders() throws SQLException;

    public boolean deleteOrder(int id) throws SQLException;

    public boolean updateOrder(Order Order) throws SQLException;

    public boolean updateOrderWithSP(Order Order) throws SQLException;

    public boolean checkEmailExists(String email) throws SQLException;
}
