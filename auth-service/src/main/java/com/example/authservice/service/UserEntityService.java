package com.example.authservice.service;

import com.example.authservice.model.UserEntity;

public interface UserEntityService {

    UserEntity findByUsername(String username);

    UserEntity findById(Long id);

    UserEntity approve(Long id);
}
