package com.codegym.dao.impl;

import com.codegym.model.User;
import com.codegym.dao.DatabaseQuery;
import com.codegym.dao.IUserDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DatabaseQuery implements IUserDAO {

    private static final String INSERT_USER = "INSERT INTO `users` (`name`, `email`, `idCountry`) " +
            "VALUES (?, ?, ?)";
    private static final String CHECK_EMAIL_EXISTS = "SELECT * FROM users where email = ?";
    private static final String SP_EDIT_USER = "call c6_customermanager.sp_editUser(?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_USER_PAGGING_FILLTER = "select SQL_CALC_FOUND_ROWS * from users where name like ? and idCountry = ? limit ?, ?;";
    private static final String SELECT_ALL_USER_PAGGING_FILLTER_ALL = "select SQL_CALC_FOUND_ROWS * from users where name like ? limit ?, ?;";

    private int noOfRecords;


    private String SELECT_ALL_USERS = "SELECT id, name, email, idcountry FROM users";
    private String SELECT_USER_BYID = "SELECT id, name, email, idcountry FROM users where id = ?";


    @Override
    public boolean checkUserNamePassword(String username, String password) {
        if (username.equals("admin") && password.equals("123123")) {
            return true;
        }
        if (username.equals("user") && password.equals("123123")) {
            return true;
        }
        return false;
    }
    public  boolean checkUserExists(String username, String password){
        if (username == null || password == null) {
            return false;
        }
        if(username.equals("quang")&&password.equals("123123")){
            return true;
        }
        if(username.equals("admin")&&password.equals("123123")){
            return true;
        }
        if(username.equals("thien")&&password.equals("123123")){
            return true;
        }
        return false;
    }
    @Override
    public  List<String> getUserRoles(String username){
        List<String> roles = new ArrayList<>();
        // Xuong database lay role len
        if(username.equals("admin")){
            roles.add("admin");
        }
        if (username.equals("quang")) {
            roles.add("user");
        }
        return roles;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }

    @Override
    public List<User> selectAllUsersPaggingFilter(int offset, int noOfRecords, String q, int idCountry){
        List<User> userList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {

            Connection connection = getConnection();
            if(idCountry!=-1){
                preparedStatement = connection.prepareStatement(SELECT_ALL_USER_PAGGING_FILLTER);
                // select SQL_CALC_FOUND_ROWS * from users where name like ? and idCountry = ? limit ?, ?;
                // idcountry = -1 => lấy tất cả
                // idcountry != -1
                preparedStatement.setString(1,"%" + q + "%" );
                preparedStatement.setInt(2, idCountry);
                preparedStatement.setInt(3, offset);
                preparedStatement.setInt(4, noOfRecords);
            }else{
                preparedStatement = connection.prepareStatement(SELECT_ALL_USER_PAGGING_FILLTER_ALL);
                preparedStatement.setString(1, "%" + q + "%");
                preparedStatement.setInt(2, offset);
                preparedStatement.setInt(3, noOfRecords);
            }
            System.out.println(this.getClass() + " selectAllUsersPaggingFilter " + preparedStatement);


            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = getUserFromResultSet(rs);
                userList.add(user);
            }
            rs = preparedStatement.executeQuery("SELECT FOUND_ROWS();");
            if(rs.next())
                this.noOfRecords = rs.getInt(1);

            return userList;
        } catch (SQLException exception) {
            printSQLException(exception);
        }
        return null;
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException{
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        int countryId = rs.getInt("idCountry");

        User user = new User(id, name, email, countryId);
        return user;
    }

    @Override
    public void insertUser(User user) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setInt(3, user.getIdCountry());

        preparedStatement.executeUpdate();

    }


    @Override
    public User selectUser(int id) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparableStatement = connection.prepareStatement(SELECT_USER_BYID);
            preparableStatement.setInt(1 , id);

            ResultSet rs = preparableStatement.executeQuery();

            System.out.println(this.getClass() + " selectUser: " + preparableStatement);
            while (rs.next()){
                int idUser = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int idCountry = rs.getInt("idCountry");
                User user = new User(idUser, name, email, idCountry);
                return user;
            }
            connection.close();
        }catch (SQLException ex){
            printSQLException(ex);
        }
        return null;
    }

    @Override
    public List<User> selectAllUsers() {

        List<User> listUser = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparableStatement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet rs = preparableStatement.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int idCountry = rs.getInt("idCountry");
                User user = new User(id, name, email, idCountry);
                listUser.add(user);
            }
            connection.close();
        }catch (SQLException ex){
            printSQLException(ex);
        }
        return listUser;
    }

    public  List<User> selectAllUsersStatement(){
        List<User> listUser = new ArrayList<>();
        try{
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(SELECT_ALL_USERS);
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int idCountry = rs.getInt("id");
                User user = new User(id, name, email, idCountry);
                listUser.add(user);
            }
        }catch (SQLException ex){
            printSQLException(ex);
        }
        return listUser;
    }

    @Override
    public boolean deleteUser(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        return false;
    }

    @Override
    public boolean updateUserWithSP(User user) throws SQLException {
        Connection connection = getConnection();
        //call c6_customermanager.sp_editUser(?, ?, ?, ?, ?)
        CallableStatement callableStatement = connection.prepareCall(SP_EDIT_USER);
        callableStatement.setInt(1, user.getId());
        callableStatement.setString(2, user.getName());
        callableStatement.setString(3, user.getEmail());
        callableStatement.setInt(4, user.getIdCountry());
        callableStatement.registerOutParameter(5, Types.VARCHAR);

        System.out.println(this.getClass() + " updateUserWithSP " + callableStatement);
        callableStatement.executeUpdate();
        String message = callableStatement.getString(5);
        System.out.println("Message: " + message);

        return true;
    }

    @Override
    public boolean checkEmailExists(String email) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CHECK_EMAIL_EXISTS);
        preparedStatement.setString(1, email);

        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()){
            return true;
        }
        return false;
    }


}
