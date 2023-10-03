package com.cinqucento.engineservicestarter.config;

import com.cinqucento.engineservicestarter.dto.*;
import com.cinqucento.engineservicestarter.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(EngineServiceStarterProperties.class)
public class EngineServiceAutoConfiguration {

    private final EngineServiceStarterProperties properties;

    @Autowired
    public EngineServiceAutoConfiguration(EngineServiceStarterProperties properties) {
        this.properties = properties;
    }

    @Bean
    public CommentEntityDTO commentEntityDTO() {
        return new CommentEntityDTO();
    }

    @Bean
    public CommentEntityResponse commentEntityResponse() {
        return new CommentEntityResponse();
    }

    @Bean
    public ProjectEntityDTO projectEntityDTO() {
        return new ProjectEntityDTO();
    }

    @Bean
    public ProjectEntityResponse projectEntityResponse() {
        return new ProjectEntityResponse();
    }

    @Bean
    public TaskEntityDTO taskEntityDTO() {
        return new TaskEntityDTO();
    }

    @Bean
    public TaskEntityResponse taskEntityResponse() {
        return new TaskEntityResponse();
    }

    @Bean
    public CommentEntityNotCreatedException commentEntityNotCreatedException() {
        return new CommentEntityNotCreatedException(properties.getErrorCode(), properties.getExceptionMessage());
    }

    @Bean
    public CommentEntityNotDeletedException commentEntityNotDeletedException() {
        return new CommentEntityNotDeletedException(properties.getErrorCode(), properties.getExceptionMessage());
    }

    @Bean
    public CommentEntityNotFoundException commentEntityNotFoundException() {
        return new CommentEntityNotFoundException(properties.getErrorCode(), properties.getExceptionMessage());
    }

    @Bean
    public CommentEntityNotUpdatedException commentEntityNotUpdatedException() {
        return new CommentEntityNotUpdatedException(properties.getErrorCode(), properties.getExceptionMessage());
    }

    @Bean
    public ProjectEntityNotCreatedException ProjectEntityNotCreatedException() {
        return new ProjectEntityNotCreatedException(properties.getErrorCode(), properties.getExceptionMessage());
    }

    @Bean
    public ProjectEntityNotDeletedException projectEntityNotDeletedException() {
        return new ProjectEntityNotDeletedException(properties.getErrorCode(), properties.getExceptionMessage());
    }

    @Bean
    public ProjectEntityNotFoundException projectEntityNotFoundException() {
        return new ProjectEntityNotFoundException(properties.getErrorCode(), properties.getExceptionMessage());
    }

    @Bean
    public ProjectEntityNotUpdatedException projectEntityNotUpdatedException() {
        return new ProjectEntityNotUpdatedException(properties.getErrorCode(), properties.getExceptionMessage());
    }

    @Bean
    public TaskEntityNotCreatedException taskEntityNotCreatedException() {
        return new TaskEntityNotCreatedException(properties.getErrorCode(), properties.getExceptionMessage());
    }

    @Bean
    public TaskEntityNotDeletedException taskEntityNotDeletedException() {
        return new TaskEntityNotDeletedException(properties.getErrorCode(), properties.getExceptionMessage());
    }

    @Bean
    public TaskEntityNotFoundException taskEntityNotFoundException() {
        return new TaskEntityNotFoundException(properties.getErrorCode(), properties.getExceptionMessage());
    }

    @Bean
    public TaskEntityNotUpdatedException taskEntityNotUpdatedException() {
        return new TaskEntityNotUpdatedException(properties.getErrorCode(), properties.getExceptionMessage());
    }

    @Bean
    public IllegalSortDirection illegalSortDirection() {
        return new IllegalSortDirection(properties.getErrorCode(), properties.getExceptionMessage());
    }

    @Bean
    public IllegalSortArgumentException illegalSortArgumentException() {
        return new IllegalSortArgumentException(properties.getErrorCode(), properties.getExceptionMessage());
    }

    @Bean
    public UserAccessDeniedException userAccessDeniedException() {
        return new UserAccessDeniedException(properties.getErrorCode(), properties.getExceptionMessage());
    }

    @Bean
    public JWTExpiredException jwtExpiredException() {
        return new JWTExpiredException(properties.getErrorCode(), properties.getExceptionMessage());
    }
}
