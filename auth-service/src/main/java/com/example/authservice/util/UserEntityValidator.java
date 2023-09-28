package com.example.authservice.util;

import com.cinquecento.authservicestarter.exception.UserEntityNotFoundException;
import com.example.authservice.model.UserEntity;
import com.example.authservice.service.impl.UserEntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserEntityValidator implements Validator {

    private final UserEntityServiceImpl userEntityService;

    @Autowired
    public UserEntityValidator(UserEntityServiceImpl userEntityService) {
        this.userEntityService = userEntityService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserEntity.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserEntity user = (UserEntity) target;

        UserEntity foundUser = null;

        try {
             foundUser = userEntityService.findByUsername(user.getUsername());
        } catch (UserEntityNotFoundException e) {

        }

        if (foundUser != null) {
            errors.rejectValue("username", "", "This username is already taken.");
        }
    }
}
