package com.client.info.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

	@RequestMapping(value="/person/findPersonInfo.*")
	public String findPersonInfo() throws Exception{
		return "{age:18,name:'person'}";
	}
}
