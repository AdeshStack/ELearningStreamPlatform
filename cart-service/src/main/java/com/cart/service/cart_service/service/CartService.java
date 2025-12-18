package com.cart.service.cart_service.service;


import com.cart.service.cart_service.dao.CartRequest;
import com.cart.service.cart_service.dao.CartResponse;
import com.cart.service.cart_service.entity.Cart;
import com.cart.service.cart_service.repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;

    public Cart create(Cart request){

        return cartRepo.save(request);
    }

    public CartResponse addToCart(CartRequest request){
        //find the cart where we neeed to add the course id

        Cart cart=cartRepo.findById(request.userId()).orElseThrow(()->new RuntimeException("Id not found"));

        cart.getCourse().add(request.courseId());// this list of course id



        return CartResponse.builder()
                .course(cartRepo.save(cart).getCourse())
                .build();
    }

    public List<CartResponse> getAllCartByAuthor(Long userId){

        List<Cart> carts=cartRepo.findAll();

        List<CartResponse> response=carts.stream().map(cart->mapToCart(cart)).toList();

        return response;

    }

    public CartResponse mapToCart(Cart cart){
        return CartResponse.builder()
                .course(cart.getCourse())
                .build();
    }
}
