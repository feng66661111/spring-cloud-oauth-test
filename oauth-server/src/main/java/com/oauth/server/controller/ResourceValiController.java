package com.oauth.server.controller;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceValiController {
	
	@Autowired
	private AuthorizationServerEndpointsConfiguration endPoints;

	@RequestMapping(value="/user", produces="application/json")
	public Map<String, Object> findUserInfo(HttpServletRequest request){
		Map<String, Object> userMap = new LinkedHashMap<>();
		Enumeration<String> er = request.getHeaderNames();
		String tokenValue = null;
		while(er.hasMoreElements()){
			String key = er.nextElement();
			System.out.println(key+",");
			if(StringUtils.equals(key, "authorization")){
				String value = request.getHeader(key);
				String [] values = StringUtils.split(value," ");
				tokenValue = values[1];
				break;
			}
		}
//		SecurityContext sc = SecurityContextHolder.getContext();
//		String user = "";
//		if(sc != null){
//			Authentication at = sc.getAuthentication();
//			if(at != null){
//				user = (String)at.getPrincipal();
//			}
//		}
		if(StringUtils.isNotBlank(tokenValue)){
			InMemoryTokenStore store = (InMemoryTokenStore)endPoints.getEndpointsConfigurer().getTokenStore();
			OAuth2AccessToken token = store.readAccessToken(tokenValue);
			OAuth2Authentication auth = store.readAuthentication(token);
			if(auth != null){
				userMap.put("user", auth.getUserAuthentication().getPrincipal());
				userMap.put("authorities", AuthorityUtils.authorityListToSet(auth.getUserAuthentication().getAuthorities()));
			}
		}
		System.out.println(userMap);
		return userMap;
	}
}
