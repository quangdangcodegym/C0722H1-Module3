package com.example.helloc6.dao.impl;

import com.example.helloc6.dao.DatabaseQuery;
import com.example.helloc6.dao.IOrderItemDTODAO;
import com.example.helloc6.model.Product;
import com.example.helloc6.model.dto.OrderItemDTO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDTODAO extends DatabaseQuery implements IOrderItemDTODAO {
    private static final String SELECT_ALL_ORDERITEMDTO_BY_ORDERID = "SELECT oi.id as oiid, oi.orderid as oiorderid, " +
            "oi.quantity as oiquantity, oi.price as oiprice,\n" +
            " p.id as pid, p.name as pname, p.description as pdescription \n" +
            " FROM orderitem oi join product p on oi.productid = p.id and oi.orderid = ?;";

    @Override
    public void insertOrderItemDTO(OrderItemDTO orderItemDTO) throws SQLException {

    }

    @Override
    public OrderItemDTO selectOrderItemDTO(int id) throws SQLException {
        return null;
    }

    @Override
    public List<OrderItemDTO> selectAllOrderItemDTOByOrderId(int idOrder) throws SQLException {
        List<OrderItemDTO> listOrderItemDTO = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparableStatement = connection.prepareStatement(SELECT_ALL_ORDERITEMDTO_BY_ORDERID);
            preparableStatement.setInt(1, idOrder);
            ResultSet rs = preparableStatement.executeQuery();

            while (rs.next()){
                OrderItemDTO orderItemDTO = getOrderItemDTOFromResultSet(rs);
                listOrderItemDTO.add(orderItemDTO);
            }
            connection.close();
        }catch (SQLException ex){
            printSQLException(ex);
        }
        return listOrderItemDTO;
    }

    private OrderItemDTO getOrderItemDTOFromResultSet(ResultSet rs) throws SQLException{
        /**
         SELECT oi.id as oiid, oi.orderid as oiorderid, oi.quantity as oiquantity, oi.price as oiprice,
         p.id as pid, p.name as pname, p.description as pdescription
         */
        int idOrderItem = rs.getInt("oiid");
        int idOrder = rs.getInt("oiorderid");
        int quantity = rs.getInt("oiquantity");
        BigDecimal price = rs.getBigDecimal("oiprice");
        int idProduct = rs.getInt("pid");
        String name = rs.getString("pname");
        String description = rs.getString("pdescription");

        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setId(idOrderItem);
        orderItemDTO.setIdOrder(idOrder);
        orderItemDTO.setQuantity(quantity);
        orderItemDTO.setPrice(price);

        Product product = new Product();
        product.setId(idProduct);
        product.setName(name);
        product.setDescription(description);

        orderItemDTO.setProduct(product);

        return orderItemDTO;
    }

    @Override
    public boolean deleteOrderItemDTO(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateOrderItemDTO(OrderItemDTO orderItemDTO) throws SQLException {
        return false;
    }

    @Override
    public boolean updateOrderItemDTOWithSP(OrderItemDTO orderItemDTO) throws SQLException {
        return false;
    }

    @Override
    public boolean checkEmailExists(String email) throws SQLException {
        return false;
    }
}
