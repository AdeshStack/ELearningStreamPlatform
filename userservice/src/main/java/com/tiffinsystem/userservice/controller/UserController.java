package com.tiffinsystem.userservice.controller;

import com.tiffinsystem.userservice.Dao.UserRequest;
import com.tiffinsystem.userservice.Dao.UserResponse;
import com.tiffinsystem.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {



    @Autowired
    private UserService service;

    @PostMapping("/create")
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest request){

        return new ResponseEntity<>(this.service.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/update/{userId}")

    public ResponseEntity<UserResponse> update(@RequestBody UserRequest request, @PathVariable("userId") Long userId){

        return new ResponseEntity<>(this.service.update(userId,request),HttpStatus.OK );
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<UserResponse> getById(@PathVariable ("userId") Long userId ){

        return new ResponseEntity<>(this.service.getById(userId), HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> delete(@PathVariable("userId") Long userId){
        this.service.delete(userId);
        return new ResponseEntity<>("User Deleted "+userId,HttpStatus.OK);
    }

    @GetMapping("/user/all")
    public ResponseEntity<List<UserResponse>>getAll(){

        return new ResponseEntity<>(this.service.getAll(),HttpStatus.OK);
    }


}