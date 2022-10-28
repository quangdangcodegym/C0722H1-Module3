package com.example.helloc6.model;

public class Category {
    private int id;
    private String name;
    private int parentId;
    private String description;

    public Category() {

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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category(int id, String name, int parentId, String description) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.description = description;
    }
}
