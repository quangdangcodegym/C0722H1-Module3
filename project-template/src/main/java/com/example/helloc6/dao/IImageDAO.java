package com.example.helloc6.dao;

import com.example.helloc6.model.Image;

import java.sql.SQLException;
import java.util.List;

public interface IImageDAO {
    public void insertImage(Image Image) throws SQLException;

    public Image selectImage(int id);

    public List<Image> selectAllImages();

    public List<Image> selectImagesByProductId(int idProduct) throws SQLException;

    public boolean deleteImage(int id) throws SQLException;

    public boolean updateImage(Image Image) throws SQLException;

    public boolean updateImageWithSP(Image Image) throws SQLException;

    public boolean checkEmailExists(String email) throws SQLException;
}
