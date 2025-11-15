package com.tiffinsystem.userservice.service;

import com.tiffinsystem.userservice.Dao.UserRequest;
import com.tiffinsystem.userservice.Dao.UserResponse;
import java.util.List;


public interface UserService {

    UserResponse create(UserRequest userDto);

    UserResponse update(Long id,UserRequest userDto);

    UserResponse getById(Long id);

    void  delete (Long id);

    List<UserResponse> getAll();

}
