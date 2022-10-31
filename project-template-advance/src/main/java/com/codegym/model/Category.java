package com.codegym.model;

import java.math.BigInteger;

public class Category {
    private Long id;
    private Long parentId;
    private String title;
    private String metaTitle;
    private String slug;
    private String content;

    public Category(){

    }
    public Category(Long id, Long parentId, String title, String metaTitle, String slug, String content) {
        this.id = id;
        this.parentId = parentId;
        this.title = title;
        this.metaTitle = metaTitle;
        this.slug = slug;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
