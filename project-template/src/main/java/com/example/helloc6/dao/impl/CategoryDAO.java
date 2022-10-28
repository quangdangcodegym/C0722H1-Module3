package com.example.helloc6.dao.impl;

import com.example.helloc6.dao.DatabaseQuery;
import com.example.helloc6.dao.ICategoryDAO;
import com.example.helloc6.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends DatabaseQuery implements ICategoryDAO {
    private static final String SELECT_ALL_CATEGORY_BYID = "SELECT id, name, parentid, description FROM category where id = ?;";
    private static final String SELECT_ALL_CATEGORY = "SELECT id, name, parentid, description FROM category";

    @Override
    public Category selectCategory(int id) {
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORY_BYID);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectAllCategory " + preparedStatement);
            while (rs.next()){
                Category category = getCategoryFromResultSet(rs);
                return category;
            }
            connection.close();
        }catch (SQLException ex){
            printSQLException(ex);
        }

        return null;
    }

    private Category getCategoryFromResultSet(ResultSet rs) throws SQLException{
        //SELECT id, name, parentid, description FROM category;
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int parentId = rs.getInt("parentid");
        String description = rs.getString("description");

        Category category = new Category(id, name, parentId, description);
        return category;
    }

    @Override
    public List<Category> selectAllCategory() {
        List<Category> listCategory = new ArrayList<>();
        try{
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORY);

            ResultSet rs = preparedStatement.executeQuery();
            System.out.println(this.getClass() + " selectAllCategory " + preparedStatement);
            while (rs.next()){
                Category category = getCategoryFromResultSet(rs);
                listCategory.add(category);
            }
            connection.close();
        }catch (SQLException ex){
            printSQLException(ex);
        }

        return listCategory;
    }
}
