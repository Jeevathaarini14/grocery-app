package com.groceryshop.grocery_web_app.model;

import jakarta.persistence.*;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public String productName;
    public Integer product_price;
    public String product_description;
    public String product_image;

    public Product(String productName, Integer product_price, String product_description, String product_image) {
        this.productName = productName;
        this.product_price = product_price;
        this.product_description = product_description;
        this.product_image = product_image;
    }

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Integer product_price) {
        this.product_price = product_price;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }
}
