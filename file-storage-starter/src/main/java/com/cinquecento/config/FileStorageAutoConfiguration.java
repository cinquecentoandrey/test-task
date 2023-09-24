package com.cinquecento.config;

import com.cinquecento.dto.FileEntityDTO;
import com.cinquecento.dto.FileEntityReferenceResponse;
import com.cinquecento.dto.FileEntityResponse;
import com.cinquecento.exception.FileEntityNotDeletedException;
import com.cinquecento.exception.FileEntityNotFoundException;
import com.cinquecento.exception.FileEntityNotSavedException;
import com.cinquecento.exception.FileEntityNotUpdatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties(FileStorageStarterProperties.class)
public class FileStorageAutoConfiguration {

    private final FileStorageStarterProperties properties;

    @Autowired
    public FileStorageAutoConfiguration(FileStorageStarterProperties properties) {
        this.properties = properties;
    }

    @Bean
    public FileEntityNotDeletedException fileEntityNotDeletedException() {
        return new FileEntityNotDeletedException(properties.getErrorCode(), properties.getExceptionMessage());
    }

    @Bean
    public FileEntityNotUpdatedException fileEntityNotUpdatedException() {
        return new FileEntityNotUpdatedException(properties.getErrorCode(), properties.getExceptionMessage());
    }

    @Bean
    public FileEntityNotSavedException fileEntityNotSavedException() {
        return new FileEntityNotSavedException(properties.getErrorCode(), properties.getExceptionMessage());
    }

    @Bean
    public FileEntityNotFoundException fileEntityNotFoundException() {
        return new FileEntityNotFoundException(properties.getErrorCode(), properties.getExceptionMessage());
    }

    @Bean
    public FileEntityDTO fileEntityDTO() {
        return new FileEntityDTO();
    }

    @Bean
    public FileEntityResponse fileEntityResponse() {
        return new FileEntityResponse();
    }

    @Bean
    public FileEntityReferenceResponse fileEntityReferenceResponse() {
        return new FileEntityReferenceResponse();
    }
}
