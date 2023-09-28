package com.cinquecento.authservicestarter.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "auth-starter")
@Getter
@Setter
public class AuthServiceStarterProperties {

    private Integer errorCode = 0;
    private String exceptionMessage = "Default message";

}
