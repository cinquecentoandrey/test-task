package com.cinquecento.authservicestarter.config;

import com.cinquecento.authservicestarter.dto.AuthenticationUserEntityDTO;
import com.cinquecento.authservicestarter.dto.RegistrationUserEntityDTO;
import com.cinquecento.authservicestarter.dto.UserEntityDTO;
import com.cinquecento.authservicestarter.dto.UserEntityResponse;
import com.cinquecento.authservicestarter.exception.UserEntityNotCreatedException;
import com.cinquecento.authservicestarter.exception.UserEntityNotFoundException;
import com.cinquecento.authservicestarter.exception.UserEntityNotLoginException;
import com.cinquecento.authservicestarter.exception.UserIsNotApprovedException;
import com.cinquecento.authservicestarter.util.ErrorMessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AuthServiceStarterProperties.class)
public class AuthServiceAutoConfiguration {

    private final AuthServiceStarterProperties properties;

    @Autowired
    public AuthServiceAutoConfiguration(AuthServiceStarterProperties properties) {
        this.properties = properties;
    }

    @Bean
    public AuthenticationUserEntityDTO authenticationUserEntityDTO() {
        return new AuthenticationUserEntityDTO();
    }

    @Bean
    public RegistrationUserEntityDTO registrationUserEntityDTO() {
        return new RegistrationUserEntityDTO();
    }

    @Bean
    public UserEntityDTO userEntityDTO() {
        return new UserEntityDTO();
    }

    @Bean
    public UserEntityResponse userEntityResponse() {
        return new UserEntityResponse();
    }

    @Bean
    public UserEntityNotCreatedException userEntityNotCreatedException() {
        return new UserEntityNotCreatedException(properties.getErrorCode(), properties.getExceptionMessage());
    }

    @Bean
    public UserEntityNotFoundException userEntityNotFoundException() {
        return new UserEntityNotFoundException(properties.getErrorCode(), properties.getExceptionMessage());
    }

    @Bean
    public UserEntityNotLoginException userEntityNotLoginException() {
        return new UserEntityNotLoginException(properties.getErrorCode(), properties.getExceptionMessage());
    }

    @Bean
    public UserIsNotApprovedException userIsNotApprovedException() {
        return new UserIsNotApprovedException(properties.getErrorCode(), properties.getExceptionMessage());
    }

    @Bean
    public ErrorMessageBuilder errorMessageBuilder() {
        return new ErrorMessageBuilder();
    }
}
