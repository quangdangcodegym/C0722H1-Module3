package com.codegym.mapper;

import com.codegym.model.Country;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryMapper implements RowMapper<Country> {
    @Override
    public Country mapRow(ResultSet rs) {
        try {
            int idCountry = rs.getInt("id");
            String name = rs.getString("name");
            Country country = new Country(idCountry, name);
            return country;
        } catch (SQLException sqlException) {
            return null;
        }
    }
}
