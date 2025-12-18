package com.tiffinsystem.userservice.service;


import com.tiffinsystem.userservice.Dao.UserRequest;
import com.tiffinsystem.userservice.Dao.UserResponse;
import com.tiffinsystem.userservice.entity.User;
import com.tiffinsystem.userservice.repository.userRepo;

import com.tiffinsystem.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private  userRepo userRepo;

    @Override
    public UserResponse create(UserRequest userDto) {

        User user=User.builder()
                .name(userDto.getName())
                .role(userDto.getRole())
                .phone(userDto.getPhone())
                .status("Active")
                .email(userDto.getEmail())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .updatedAt(new Timestamp(System.currentTimeMillis()))
                .password(userDto.getPassword())
                .build();

        User save = this.userRepo.save(user);

        UserResponse response = UserResponse.builder()

                .id(save.getId())
                .name(save.getName())
                .status(save.getStatus())
                .role(save.getRole())
                .phone(save.getPhone())
                .email(save.getEmail())
                .createdAt(save.getCreatedAt())
                .updatedAt(save.getUpdatedAt())
                .build();

        return  response;


    }

    @Override
    public UserResponse update(Long id, UserRequest userDto) {


      User user=this.userRepo.findById(id).orElseThrow();
      user.setName(userDto.getName());
      user.setEmail(userDto.getEmail());
      user.setPhone(userDto.getPhone());
      user.setRole(userDto.getRole());

      this.userRepo.save(user);

      UserResponse response= UserResponse.builder()
              .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
              .phone(user.getPhone())
              .role(user.getRole())
              .status(user.getStatus())
              .createdAt(user.getCreatedAt())
              .updatedAt(user.getUpdatedAt())
              .build();



  return response;
    }

    @Override
    public UserResponse getById(Long id) {
        User user=this.userRepo.findById(id).orElseThrow();
        UserResponse response= UserResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole())
                .status(user.getStatus())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();

        return response;
    }

    @Override
    public void delete(Long id) {

        User user=this.userRepo.findById(id).orElseThrow();
        logger.info("User going to be deleted "+user.getId());
        this.userRepo.delete(user);

    }

    @Override
    public List<UserResponse> getAll() {

        List<User> listUser=this.userRepo.findAll();
        //map to userresponse

        List<UserResponse> responseList= listUser.stream().map(
                user -> UserResponse.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .phone(user.getPhone())
                        .role(user.getRole())
                        .status(user.getStatus())
                        .createdAt(user.getCreatedAt())
                        .updatedAt(user.getUpdatedAt())
                        .build()
        ).collect(Collectors.toList());

        return  responseList;

    }
}
