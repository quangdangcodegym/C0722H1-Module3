package com.example.helloc6.dao.impl;

import com.example.helloc6.dao.DatabaseQuery;
import com.example.helloc6.dao.IOrderDAO;
import com.example.helloc6.model.Order;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DatabaseQuery implements IOrderDAO {
    private static final String SELECT_ALL_ORDER = "SELECT id, name, phone, address, idstatus, total FROM `order`;";
    private static final String SELECT_ORDER_BYID = "SELECT id, name, phone, address, idstatus, total FROM `order` where id = ?;";

    @Override
    public void insertOrder(Order Order) throws SQLException {
        
    }

    @Override
    public Order selectOrder(int id) throws SQLException {
        try{
            Connection connection = getConnection();
            PreparedStatement preparableStatement = connection.prepareStatement(SELECT_ORDER_BYID);
            preparableStatement.setInt(1 , id);

            ResultSet rs = preparableStatement.executeQuery();

            System.out.println(this.getClass() + " selectOrder: " + preparableStatement);
            while (rs.next()){
                Order order = getOrderFromResultSet(rs);
                return order;
            }
            connection.close();
        }catch (SQLException ex){
            printSQLException(ex);
        }
        return null;
    }

    @Override
    public List<Order> selectAllOrders() throws SQLException {
        List<Order> listOrder = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparableStatement = connection.prepareStatement(SELECT_ALL_ORDER);
            ResultSet rs = preparableStatement.executeQuery();

            while (rs.next()){
                //SELECT id, name, phone, address, idstatus, total FROM `order`;
                Order order = getOrderFromResultSet(rs);
                listOrder.add(order);
            }
            connection.close();
        }catch (SQLException ex){
            printSQLException(ex);
        }
        return listOrder;
    }

    private Order getOrderFromResultSet(ResultSet rs) throws SQLException{
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String phone = rs.getString("phone");
        String address = rs.getString("address");
        int idStatus = rs.getInt("idstatus");
        BigDecimal total = rs.getBigDecimal("total");
        Order order = new Order(id, name, phone, address, idStatus, total);
        return order;

    }

    @Override
    public boolean deleteOrder(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateOrder(Order Order) throws SQLException {
        return false;
    }

    @Override
    public boolean updateOrderWithSP(Order Order) throws SQLException {
        return false;
    }

    @Override
    public boolean checkEmailExists(String email) throws SQLException {
        return false;
    }
}
