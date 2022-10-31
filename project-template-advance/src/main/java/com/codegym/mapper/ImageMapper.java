package com.codegym.mapper;

import com.codegym.model.Image;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageMapper implements RowMapper<Image>{
    @Override
    public Image mapRow(ResultSet rs) {
        try {
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
        } catch (SQLException sqlException) {
            return null;
        }
    }
}
