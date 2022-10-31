package com.codegym.mapper;

import com.codegym.model.Order;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order>{
    @Override
    public Order mapRow(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String phone = rs.getString("phone");
            String address = rs.getString("address");
            int idStatus = rs.getInt("idstatus");
            BigDecimal total = rs.getBigDecimal("total");
            Order order = new Order(id, name, phone, address, idStatus, total);
            return order;
        } catch (SQLException sqlException) {
            return null;
        }
    }
}
