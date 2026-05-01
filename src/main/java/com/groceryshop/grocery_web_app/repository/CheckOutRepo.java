package com.groceryshop.grocery_web_app.repository;

import com.groceryshop.grocery_web_app.model.CheckOut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckOutRepo extends JpaRepository <CheckOut,Long> {
}
