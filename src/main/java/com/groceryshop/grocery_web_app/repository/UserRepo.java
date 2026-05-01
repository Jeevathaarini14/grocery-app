package com.groceryshop.grocery_web_app.repository;

import com.groceryshop.grocery_web_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
