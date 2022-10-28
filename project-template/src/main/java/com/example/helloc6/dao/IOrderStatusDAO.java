package com.example.helloc6.dao;


import com.example.helloc6.model.OrderStatus;

import java.sql.SQLException;
import java.util.List;

public interface IOrderStatusDAO {
    public OrderStatus selectOrderStatus(int id) throws SQLException;

    public List<OrderStatus> selectAllOrderStatuss() throws SQLException;
}
