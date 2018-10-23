package com.resource.server.controller;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceValiController {

	@RequestMapping(value="/user", produces="application/json")
	public Map<String, Object> findUserInfo(OAuth2Authentication user){
		Map<String, Object> userMap = new LinkedHashMap<>();
		userMap.put("user", user.getUserAuthentication().getPrincipal());
		userMap.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
		return userMap;
	}
	
	@RequestMapping("/userInfo")
    public Principal user(Principal principal,HttpServletRequest request) {
        System.out.println(principal);
        Map<String, String []> map = request.getParameterMap();
        if(map != null){
        	for(String s : map.keySet()){
        		System.out.println(s+","+map.get(s));
        	}
        }
        return principal;
    }
}
