package com.example.authservice.service;

import com.example.authservice.model.UserEntity;
import com.example.authservice.repository.UserEntityRepository;
import com.example.authservice.secutiry.UserEntityDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserEntityDetailsService implements UserDetailsService {

    private final UserEntityRepository userEntityRepository;

    @Autowired
    public UserEntityDetailsService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<UserEntity> userEntity = userEntityRepository.findByUsername(username);

        if (!userEntity.isPresent()) {
            throw new UsernameNotFoundException("User with username " + username + " not found.");
        }

        return new UserEntityDetails(userEntity.get());
    }
}
