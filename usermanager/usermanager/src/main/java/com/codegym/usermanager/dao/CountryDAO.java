package com.codegym.usermanager.dao;

import com.codegym.usermanager.model.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO extends DatabaseContext implements ICountryDAO{
    private static final String SELECT_ALL_COUNTRY = "SELECT * FROM country;";
    private static final String SELECT_COUNTRY_BY_ID = "SELECT * FROM country where id = ?;";


    @Override
    public void insertCountry(Country country) {

    }

    @Override
    public Country selectCountry(int id) {
        List<Country> listCountry = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COUNTRY_BY_ID);
            preparedStatement.setInt(1, id);


            System.out.println(this.getClass() + " selectCountry: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Country country = getCountryFromResultSet(rs);
                return country;
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return null;
    }

    @Override
    public List<Country> selectAllCountrys() {

        List<Country> listCountry = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COUNTRY);

            System.out.println(this.getClass() + " selectAllCountrys: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Country country = getCountryFromResultSet(rs);
                listCountry.add(country);
            }
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return listCountry;
    }

    private Country getCountryFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        Country country = new Country(id, name);
        return country;
    }

    @Override
    public boolean deleteCountry(int id) {
        return false;
    }

    @Override
    public boolean updateCountry(Country Country) {
        return false;
    }


}
