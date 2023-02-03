package com.edgar.sweeter.config;

import org.springframework.context.annotation.Configuration;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

@Configuration
public class MailConfiguration {
	
	private static final String APPLICATION_NAME="Sweeter";
	
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	
	private static final String  TOKENS_DIRECTORY_PATH = "tokens";

}
