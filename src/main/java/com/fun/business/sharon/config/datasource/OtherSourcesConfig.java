package com.fun.business.sharon.config.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.pms")
public class OtherSourcesConfig {

	private String url;
	private String username;
	private String password;
	
}
