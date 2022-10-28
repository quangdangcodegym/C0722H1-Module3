package com.example.helloc6.dao;

import com.example.helloc6.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    public  boolean checkUserExists(String username, String password);
    public List<String> getUserRoles(String username);
    public boolean checkUserNamePassword(String username, String password);
    public int getNoOfRecords();
    public List<User> selectAllUsersPaggingFilter(int offset, int noOfRecords, String q, int idCountry);
    public void insertUser(User user) throws SQLException;

    public User selectUser(int id) throws SQLException;

    public List<User> selectAllUsers() throws SQLException;

    public boolean deleteUser(int id) throws SQLException;

    public boolean updateUser(User user) throws SQLException;

    public boolean updateUserWithSP(User user) throws SQLException;

    public boolean checkEmailExists(String email) throws SQLException;
}
