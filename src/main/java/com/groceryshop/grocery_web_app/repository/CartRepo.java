package com.groceryshop.grocery_web_app.repository;

import com.groceryshop.grocery_web_app.model.Cart;
import com.groceryshop.grocery_web_app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository <Cart, Long> {
    Cart findByProduct(Product p);
}
