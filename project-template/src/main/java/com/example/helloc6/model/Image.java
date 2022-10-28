package com.example.helloc6.model;

public class Image {
    private int id;
    private String name;
    private String url;
    private int idProduct;
    private boolean isAvatar;

    public Image(){}
    public Image(int id, String name, String url, int idProduct, boolean isAvatar) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.idProduct = idProduct;
        this.isAvatar = isAvatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public boolean isAvatar() {
        return isAvatar;
    }

    public void setAvatar(boolean avatar) {
        isAvatar = avatar;
    }
}
