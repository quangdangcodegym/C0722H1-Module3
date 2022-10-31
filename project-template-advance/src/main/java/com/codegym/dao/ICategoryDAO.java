package com.codegym.dao;


import com.codegym.model.Category;

import java.util.List;

public interface ICategoryDAO {
    public Category selectCategory(int id);

    public List<Category> selectAllCategory();
}
