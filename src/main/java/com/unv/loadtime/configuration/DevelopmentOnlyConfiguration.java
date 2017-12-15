package com.unv.loadtime.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Profile("dev")
public class DevelopmentOnlyConfiguration {
	@Bean
	public String dummy() {
		return "something";
	}
}
