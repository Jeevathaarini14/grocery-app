package com.groceryshop.grocery_web_app.repository;

import com.groceryshop.grocery_web_app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepo extends JpaRepository<Product,Long> {


    List<Product> findByProductNameContainingIgnoreCase(String query);
}
