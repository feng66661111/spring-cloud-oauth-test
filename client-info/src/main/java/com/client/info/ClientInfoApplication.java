package com.client.info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

@SpringBootApplication
@EnableOAuth2Sso
public class ClientInfoApplication {

	@Bean
	public OAuth2RestOperations restOperations(
	  OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context) {
	    return new OAuth2RestTemplate(resource, context);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ClientInfoApplication.class, args);
	}
}
