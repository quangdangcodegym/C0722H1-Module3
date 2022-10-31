package com.codegym.mapper;

import com.codegym.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs) {
        try {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int parentId = rs.getInt("parentid");
            String description = rs.getString("description");

            Category category = new Category(id, name, parentId, description);
            return category;
        } catch (SQLException sqlException) {
            return null;
        }
    }
}
