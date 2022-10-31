package com.codegym.model;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    private int id;
    private Long userId;
    private String title;
    private String metaTitle;
    private String slug;
    private String sumary;
    private float price;
    private String sku;
    private int type;
    private float discount;
    private int quantity;
    private boolean shop;
    private Date createAt;
    private Date updateAt;
    private Date publishedAt;
    private Date startsAt;
    private Date endsAt;
    private String content;

    public Product() {

    }
    public Product(int id, Long userId, String title,
                   String metaTitle,int type, String slug, String sumary,
                   float price, String sku, float discount,
                   int quantity, boolean shop, Date createAt,
                   Date updateAt, Date publishedAt, Date startsAt,
                   Date endsAt, String content) {
        this.type  = type;
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.metaTitle = metaTitle;
        this.slug = slug;
        this.sumary = sumary;
        this.price = price;
        this.sku = sku;
        this.discount = discount;
        this.quantity = quantity;
        this.shop = shop;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.publishedAt = publishedAt;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getSumary() {
        return sumary;
    }

    public void setSumary(String sumary) {
        this.sumary = sumary;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isShop() {
        return shop;
    }

    public void setShop(boolean shop) {
        this.shop = shop;
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

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Date getStartsAt() {
        return startsAt;
    }

    public void setStartsAt(Date startsAt) {
        this.startsAt = startsAt;
    }

    public Date getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(Date endsAt) {
        this.endsAt = endsAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
