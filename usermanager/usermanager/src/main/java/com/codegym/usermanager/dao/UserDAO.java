package com.codegym.usermanager.dao;

import com.codegym.usermanager.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    private static final String SELECT_ALL_USER = "SELECT * FROM users;";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users where id = ?;";
    private static final String INSERT_USER = "insert into users(name, email, country) values(?,?,?);";
    private static final String UPDATE_USER = "UPDATE `users` " +
            "SET `name` = ?, `email` = ?, `country` = ? WHERE (`id` = ?);";
    private static final String DELETE_USER = "DELETE FROM `users` WHERE (`id` = ?);";
    private String jdbcURL = "jdbc:mysql://localhost:3306/c07_usermanager?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "St180729!!";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertUser(User user) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.executeUpdate();
            connection.close();
            System.out.println(this.getClass() + " insertUser :" + preparedStatement);
        } catch (SQLException e) {
            printSQLException(e);
        }
        //insert into users(name, email, country) values(?,?,?);"
    }

    @Override
    public User selectUser(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = getUserFromResultSet(rs);
                return user;
            }
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        int idUser = rs.getInt("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String country = rs.getString("country");
        User user = new User(idUser, name, email, country);
        return user;

    }

    @Override
    public List<User> selectAllUsers() {
        List<User> listUser = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                User user = getUserFromResultSet(rs);
                listUser.add(user);
            }
            System.out.println(this.getClass() + " selectAllUsers: " + preparedStatement);
            connection.close();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }

        return listUser;
    }

    @Override
    public boolean deleteUser(int id) {
        boolean check = false;
        Connection connection= getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
            // DELETE FROM `c07_usermanager`.`users` WHERE (`id` = '1');
            preparedStatement.setInt(1, id);

            check =  preparedStatement.executeUpdate() > 0;
            connection.close();
        }catch (SQLException e){
            printSQLException(e);
        }
        return check;

    }

    @Override
    public boolean updateUser(User user) {
        Connection connection = getConnection();
        boolean check = false;
        try {
            PreparedStatement pr = connection.prepareStatement(UPDATE_USER);
            pr.setString(1, user.getName());
            pr.setString(2, user.getEmail());
            pr.setString(3, user.getCountry());
            pr.setInt(4, user.getId());
            System.out.println(this.getClass() + " updateUser: " + pr);
            check =  pr.executeUpdate() > 0;
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return check;

    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
