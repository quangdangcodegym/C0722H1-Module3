package com.codegym.model.builder;

import com.codegym.model.Product;

import java.util.Date;

public class ProductBuilder{

    private Product product;

    /**
     public Product(int id, Long userId, String title,
     String metaTitle, String slug, String sumary,
     float price, String sku, float discount,
     int quantity, boolean shop, Date createAt,
     Date updateAt, Date publishedAt, Date startsAt,
     Date endsAt, String content)
     */
    public ProductBuilder(int id, Long userId, String title, String slug,
                          int type, String sku, float price, int quantity, boolean shop, Date createAt) {
        product = new Product();
        this.product.setId(id);
        this.product.setUserId(userId);
        this.product.setTitle(title);
        this.product.setSlug(slug);
        this.product.setType(type);
        this.product.setSku(sku);
        this.product.setPrice(price);
        this.product.setQuantity(quantity);
        this.product.setShop(shop);
        this.product.setCreateAt(createAt);
    }

    public ProductBuilder setDiscount(float discount) {
        this.product.setDiscount(discount);
        return this;
    }

    public ProductBuilder setUpdateAt(Date updateAt) {
        this.product.setUpdateAt(updateAt);
        return this;
    }
    public ProductBuilder setPublishAt(Date publishedAt) {
        this.product.setPublishedAt(publishedAt);
        return this;
    }

    public Product build() {
        return null;
    }
}
