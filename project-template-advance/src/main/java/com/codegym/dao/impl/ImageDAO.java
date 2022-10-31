package com.codegym.dao.impl;

import com.codegym.dao.IImageDAO;
import com.codegym.model.Image;
import com.codegym.dao.DatabaseQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageDAO extends DatabaseQuery implements IImageDAO {
    private static final String SELECT_ALL_IMAGE_BY_PRODUCTID = "SELECT id, name, url,isavatar FROM image where productid = ? order by isavatar desc;";

    @Override
    public void insertImage(Image Image) throws SQLException {
        
    }

    @Override
    public Image selectImage(int id) {
        return null;
    }

    @Override
    public List<Image> selectAllImages() {

        return null;
    }

    @Override
    public List<Image> selectImagesByProductId(int idProduct) throws SQLException {

        List<Image> listImage = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparableStatement = connection.prepareStatement(SELECT_ALL_IMAGE_BY_PRODUCTID);
            preparableStatement.setInt(1, idProduct);
            ResultSet rs = preparableStatement.executeQuery();

            while (rs.next()){
                Image image = getImageFromResultSet(rs);
                listImage.add(image);
            }
            connection.close();
        }catch (SQLException ex){
            printSQLException(ex);
        }
        return listImage;
    }

    private Image getImageFromResultSet(ResultSet rs) throws SQLException{
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String url = rs.getString("url");
        boolean isAvatar = rs.getBoolean("isavatar");

        //SELECT id, name, url,isavatar FROM image where productid = ?;
        Image image = new Image();
        image.setId(id);
        image.setName(name);
        image.setUrl(url);
        image.setAvatar(isAvatar);

        return image;
    }

    @Override
    public boolean deleteImage(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean updateImage(Image Image) throws SQLException {
        return false;
    }

    @Override
    public boolean updateImageWithSP(Image Image) throws SQLException {
        return false;
    }

    @Override
    public boolean checkEmailExists(String email) throws SQLException {
        return false;
    }
}
