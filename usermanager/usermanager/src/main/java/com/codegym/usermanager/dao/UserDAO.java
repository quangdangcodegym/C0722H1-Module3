package com.codegym.usermanager.dao;

import com.codegym.usermanager.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DatabaseContext implements IUserDAO {
    private static final String SELECT_ALL_USER = "SELECT * FROM users;";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users where id = ?;";
    private static final String INSERT_USER = "insert into users(name, email, idcountry) values(?,?,?);";
    private static final String UPDATE_USER = "UPDATE `users` " +
            "SET `name` = ?, `email` = ?, `idcountry` = ? WHERE (`id` = ?);";
    private static final String DELETE_USER = "DELETE FROM `users` WHERE (`id` = ?);";


    @Override
    public void insertUser(User user) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setInt(3, user.getIdCountry());
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
        int idCountry = rs.getInt("idcountry");
        User user = new User(idUser, name, email, idCountry);
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
            pr.setInt(3, user.getIdCountry());
            pr.setInt(4, user.getId());
            System.out.println(this.getClass() + " updateUser: " + pr);
            check =  pr.executeUpdate() > 0;
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return check;

    }


}
