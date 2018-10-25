package com.client.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

@RestController
public class PersonController {

	@Autowired
	private RestOperations restOperations;
	
	@RequestMapping(value="/person/findPersonInfo.*")
	public String findPersonInfo() throws Exception{
		String personResourceUrl = "http://127.0.0.1:8502/person/findPersonInfo.html";
		String result = restOperations.getForObject(personResourceUrl, String.class); 
		return result;
	}
}
