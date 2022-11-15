package com.codegym.usermanager.dao;

import java.sql.*;


public class CartDAO extends DatabaseContext implements ICartDAO{

    private static final String SP_ADDTOCART = "call sp_AddToCart(?, ?, ?, ?, ?);";

    @Override
    public void addToCart(int cartId, int productId, int quantity, double price) {
        try {
            Connection connection = getConnection();
            CallableStatement callableStatement = connection.prepareCall(SP_ADDTOCART);
            callableStatement.setInt(1, productId);
            callableStatement.setInt(2, quantity);
            callableStatement.setDouble(3, price);
            callableStatement.setInt(5, cartId);
            callableStatement.registerOutParameter(4, Types.VARCHAR);
            callableStatement.registerOutParameter(5, Types.INTEGER);

            System.out.println(this.getClass() + " addToCart: " + callableStatement);
            callableStatement.execute();

            String outMessage = callableStatement.getString(4);
            int outCartId = callableStatement.getInt(5);

            System.out.println(outMessage + ": " + outCartId);
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }
}
