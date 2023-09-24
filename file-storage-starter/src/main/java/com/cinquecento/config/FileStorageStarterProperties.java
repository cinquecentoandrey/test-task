package com.cinquecento.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "fs-starter")
@Getter
@Setter
public class FileStorageStarterProperties {

    private Integer errorCode = 0;
    private String exceptionMessage = "Default message";

}
