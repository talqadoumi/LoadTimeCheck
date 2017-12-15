package com.unv.loadtime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MainService {

	@Value("${welcome.message}")
	private String welcomeMessage;

	public String retrieveWelcomeMessage() {
		//Complex Method
		return welcomeMessage;
	}
}