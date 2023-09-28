package com.cinquecento.apigatewaystarter.config;

import com.cinquecento.apigatewaystarter.dto.MissingAuthorizationHeaderResponse;
import com.cinquecento.apigatewaystarter.exception.InvalidJWTException;
import com.cinquecento.apigatewaystarter.exception.MissingAuthorizationHeaderException;
import com.cinquecento.apigatewaystarter.exception.UserNotApprovedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ApiGatewayStarterProperties.class)
public class ApiGatewayAutoConfiguration {

    private final ApiGatewayStarterProperties properties;

    @Autowired
    public ApiGatewayAutoConfiguration(ApiGatewayStarterProperties properties) {
        this.properties = properties;
    }

    @Bean
    public MissingAuthorizationHeaderResponse missingAuthorizationHeaderResponse() {
        return new MissingAuthorizationHeaderResponse();
    }

    @Bean
    public InvalidJWTException invalidJWTException() {
        return new InvalidJWTException(properties.getErrorCode(), properties.getExceptionMessage());
    }

    @Bean
    public MissingAuthorizationHeaderException missingAuthorizationHeaderException() {
        return new MissingAuthorizationHeaderException(properties.getErrorCode(), properties.getExceptionMessage());
    }

    @Bean
    public UserNotApprovedException userNotApprovedException() {
        return new UserNotApprovedException(properties.getErrorCode(), properties.getExceptionMessage());
    }
}
