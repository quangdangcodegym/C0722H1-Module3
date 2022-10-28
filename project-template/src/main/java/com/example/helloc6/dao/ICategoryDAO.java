package com.example.helloc6.dao;


import com.example.helloc6.model.Category;

import java.sql.SQLException;
import java.util.List;

public interface ICategoryDAO {
    public Category selectCategory(int id);

    public List<Category> selectAllCategory();
}
