package com.groceryshop.grocery_web_app.model;

import jakarta.persistence.*;

@Entity
@Table (name="Cart_items")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public Integer quantity;
    @ManyToOne
    public Product product;

    public Cart(Integer quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

    public Cart() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
