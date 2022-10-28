package com.example.helloc6.model;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private int id;
    private String name;
    private Date createAt;
    private Date updateAt;
    private String description;

    private BigDecimal price;

    private int idCategory;


    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public Product(){

    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Product(int id, String name,int idCategory, BigDecimal price, Date createAt, Date updateAt, String description) {
        this.id = id;
        this.name = name;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.description = description;
        this.price = price;
        this.idCategory = idCategory;
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

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
