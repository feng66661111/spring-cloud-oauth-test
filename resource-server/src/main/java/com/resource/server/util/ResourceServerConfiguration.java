package com.resource.server.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
@EnableResourceServer
@Order(100)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Primary
    @Bean
    public RemoteTokenServices tokenServices() {
        final RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl("http://127.0.0.1:8501/oauth/check_token");
        tokenService.setClientId("client");
        tokenService.setClientSecret("secret");
        return tokenService;
    }
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/student/**").hasRole("USER")
//			.antMatchers("/person/**").hasRole("ADMIN")
			.anyRequest().authenticated();
//		http.authorizeRequests().anyRequest().authenticated();
	}
}
