package com.codegym.dao;


import com.codegym.model.OrderStatus;

import java.sql.SQLException;
import java.util.List;

public interface IOrderStatusDAO {
    public OrderStatus selectOrderStatus(int id) throws SQLException;

    public List<OrderStatus> selectAllOrderStatuss() throws SQLException;
}
