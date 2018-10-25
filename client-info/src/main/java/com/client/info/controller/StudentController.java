package com.client.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

@RestController
public class StudentController {

	@Autowired
	private RestOperations restOperations;
	
	@RequestMapping(value="/student/findStudentInfo.*")
	public String findStudentInfo() throws Exception{
		String personResourceUrl = "http://127.0.0.1:8502/student/findStudentInfo.html";
		String result = restOperations.getForObject(personResourceUrl, String.class); 
		return result;
	}
}
