package com.codegym.dao.impl;

import com.codegym.model.OrderStatus;
import com.codegym.dao.DatabaseQuery;
import com.codegym.dao.IOrderStatusDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderStatusDAO extends DatabaseQuery implements IOrderStatusDAO {

    private static final String SELECT_ORDERSTATUS_BYID = "SELECT id, name FROM orderstatus where id = ?;";
    private static final String SELECT_ALL_ORDERSTATUS = "SELECT id, name FROM orderstatus;";

    @Override
    public OrderStatus selectOrderStatus(int id) throws SQLException {
        try{
            Connection connection = getConnection();
            PreparedStatement preparableStatement = connection.prepareStatement(SELECT_ORDERSTATUS_BYID);
            preparableStatement.setInt(1 , id);

            ResultSet rs = preparableStatement.executeQuery();

            System.out.println(this.getClass() + " selectOrderStatus: " + preparableStatement);
            while (rs.next()){
                OrderStatus orderStatus = getOrderStatusFromResultSet(rs);
                return orderStatus;
            }
            connection.close();
            //preparableStatement.setString(2, name);
        }catch (SQLException ex){
            printSQLException(ex);
        }
        return null;
        
    }

    private OrderStatus getOrderStatusFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        OrderStatus orderStatus = new OrderStatus(id, name);

        return orderStatus;
    }

    @Override
    public List<OrderStatus> selectAllOrderStatuss() throws SQLException {
        List<OrderStatus> listOrderStatus = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparableStatement = connection.prepareStatement(SELECT_ALL_ORDERSTATUS);
            ResultSet rs = preparableStatement.executeQuery();

            while (rs.next()){
                OrderStatus orderStatus = getOrderStatusFromResultSet(rs);
                listOrderStatus.add(orderStatus);
            }
            connection.close();
        }catch (SQLException ex){
            printSQLException(ex);
        }
        return listOrderStatus;
    }
}
