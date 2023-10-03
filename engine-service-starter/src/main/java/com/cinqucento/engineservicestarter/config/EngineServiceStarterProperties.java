package com.cinqucento.engineservicestarter.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "es-starter")
@Getter
@Setter
public class EngineServiceStarterProperties {

    private Integer errorCode = 0;
    private String exceptionMessage = "Default message";
}
