package com.tiffinsystem.userservice.repository;

import com.tiffinsystem.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface  userRepo extends JpaRepository<User, Long> {
}
