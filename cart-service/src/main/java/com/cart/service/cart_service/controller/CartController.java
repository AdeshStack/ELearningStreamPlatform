package com.cart.service.cart_service.controller;


import com.cart.service.cart_service.dao.CartRequest;
import com.cart.service.cart_service.dao.CartResponse;
import com.cart.service.cart_service.entity.Cart;
import com.cart.service.cart_service.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<CartResponse>create(@RequestBody CartRequest cartRequest){

//        List<Long> courseId=new ArrayList<>();
//        courseId.add(cartRequest.courseId());
//        Cart cart = Cart.builder()
//                .userId(cartRequest.userId())
//                .course(courseId)
//                .build();
        Cart cart1=new Cart();
        cart1.setUserId(cartRequest.userId());
        cart1.getCourse().add(cartRequest.courseId());
        Cart response=cartService.create(cart1);

        return ResponseEntity.ok(CartResponse.builder()
                .course(response.getCourse())
                .build());

    }
    @PostMapping("/add")
    public ResponseEntity<CartResponse>addToCart(@RequestBody CartRequest cartRequest){

        return ResponseEntity.ok(this.cartService.addToCart(cartRequest));

    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartResponse>> getAllCartByUserId(@PathVariable Long userId){

        return ResponseEntity.ok(this.cartService.getAllCartByAuthor(userId));

    }


}
