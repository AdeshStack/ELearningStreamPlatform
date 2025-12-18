package com.cart.service.cart_service.repository;

import com.cart.service.cart_service.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart,Long> {


}
