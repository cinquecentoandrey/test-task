package com.example.authservice.service.impl;

import com.example.authservice.model.UserEntity;
import com.example.authservice.repository.UserEntityRepository;
import com.example.authservice.service.UserEntityRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserEntityRegistrationServiceImpl implements UserEntityRegistrationService {

    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserEntityRegistrationServiceImpl(UserEntityRepository userEntityRepository, PasswordEncoder passwordEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity register(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRole("ROLE_USER");

        return userEntityRepository.save(userEntity);
    }
}
