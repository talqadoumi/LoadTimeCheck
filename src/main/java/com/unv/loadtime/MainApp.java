package com.unv.loadtime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;



@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration.class})

@SpringBootApplication
public class MainApp {

	
	public static void main(String[] args) {
		
	ApplicationContext ctx = SpringApplication.run(
				MainApp.class, args);

	
	}
}