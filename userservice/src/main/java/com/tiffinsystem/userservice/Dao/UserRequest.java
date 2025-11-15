package com.tiffinsystem.userservice.Dao;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    private String name;
    private String email;
    private String phone;
    private String password;
    private String role;

}
