package com.example.authservice.service.impl;

import com.cinquecento.authservicestarter.exception.UserEntityNotFoundException;
import com.example.authservice.model.UserEntity;
import com.example.authservice.repository.UserEntityRepository;
import com.example.authservice.service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserEntityServiceImpl implements UserEntityService {

    @Value("${entity-error-code.not-found}")
    private Integer NOT_FOUND;

    private final UserEntityRepository userEntityRepository;

    @Autowired
    public UserEntityServiceImpl(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public UserEntity findByUsername(String username) {
        Optional<UserEntity> entity = userEntityRepository.findByUsername(username);

        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new UserEntityNotFoundException(NOT_FOUND, "User with the username = " + username + " was not found.");
        }
    }

    @Override
    public UserEntity findById(Long id) {
        Optional<UserEntity> entity = userEntityRepository.findById(id);

        if (entity.isPresent()) {
            return entity.get();
        } else {
            throw new UserEntityNotFoundException(NOT_FOUND, "User with the id = " + id + " was not found.");
        }
    }

    @Override
    public UserEntity approve(Long id) throws UserEntityNotFoundException {
        UserEntity entity = this.findById(id);
        entity.setIsApproved(true);
        return userEntityRepository.save(entity);
    }
}
