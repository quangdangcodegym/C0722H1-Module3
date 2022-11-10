package com.codegym.usermanager.dao;


import com.codegym.usermanager.model.Country;

import java.util.List;

public interface ICountryDAO {
    public void insertCountry(Country country);

    public Country selectCountry(int id);

    public List<Country> selectAllCountrys();

    public boolean deleteCountry(int id);

    public boolean updateCountry(Country Country);
}
