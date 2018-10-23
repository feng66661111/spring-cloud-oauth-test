package com.oauth.server.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{

	@Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RedisConnectionFactory redisConnectionFactory;
	
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    	clients.inMemory() // 使用in-memory存储
    		.withClient("client")
    		.secret(new BCryptPasswordEncoder().encode("secret"))
    		//该client允许的授权类型
        	.authorizedGrantTypes("authorization_code")
        	.scopes("all"); // 允许的授权范围
    }

    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
       endpoints.tokenStore(new SelfRedisTokenStore(redisConnectionFactory))
       .authenticationManager(authenticationManager);
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    	//允许表单认证
    	security.allowFormAuthenticationForClients();
    }
}
