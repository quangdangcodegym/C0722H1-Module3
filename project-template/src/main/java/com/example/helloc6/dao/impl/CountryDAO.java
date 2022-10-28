package com.example.helloc6.dao.impl;

import com.example.helloc6.dao.DatabaseQuery;
import com.example.helloc6.dao.ICountryDAO;
import com.example.helloc6.model.Country;
import com.example.helloc6.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO extends DatabaseQuery implements ICountryDAO {


    private static final String SELECT_ALL_COUNTRY = "SELECT * FROM country";
    private static final String SELECT_COUNTRY = "SELECT * FROM country where id = ?;";



    @Override
    public void insertCountry(Country country) throws SQLException {

    }

    @Override
    public Country selectCountry(int id) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COUNTRY);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                int idCountry = rs.getInt("id");
                String name = rs.getString("name");
                Country country = new Country(id, name);
                return country;
            }
            connection.close();
        }catch (SQLException ex){
            printSQLException(ex);
        }
        return null;
    }

    @Override
    public List<Country> selectAllCountry() {
        List<Country> listCountry = new ArrayList<>();
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COUNTRY);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectAllCountry " + preparedStatement);
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Country country = new Country(id, name);
                listCountry.add(country);
            }
            connection.close();
        }catch (SQLException ex){
            printSQLException(ex);
        }

        return listCountry;
    }

    @Override
    public boolean deleteCountry(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateCountry(Country country) throws SQLException {
        return false;
    }



}
