package com.cinquecento.apigatewaystarter.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "api-gateway-starter")
@Getter
@Setter
public class ApiGatewayStarterProperties {
    private Integer errorCode = 0;
    private String exceptionMessage = "Default message";
}
