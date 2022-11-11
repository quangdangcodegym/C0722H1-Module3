package com.codegym.usermanager.dao;

import com.codegym.usermanager.model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    public void insertUser(User user);

    public User selectUser(int id);

    public List<User> selectAllUsers();

    public boolean deleteUser(int id);

    public boolean updateUser(User user);

    public User findUserByEmail(String email);

    public List<User> selectAllUsers(String keyword, int idCountry, int page, int numOfPage);
    public int getNumOfRecords();
}
