package com.cinqeucento.engineservice;

import com.cinqeucento.engineservice.config.RabbitConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(RabbitConfiguration.class)
public class EngineServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EngineServiceApplication.class, args);
	}

}
