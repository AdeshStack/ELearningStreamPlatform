package com.tiffinsystem.userservice.Dao;

import lombok.*;

import java.sql.Timestamp;




@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {


    private String name;
    private String email;
    private String phone;
    private String role;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
