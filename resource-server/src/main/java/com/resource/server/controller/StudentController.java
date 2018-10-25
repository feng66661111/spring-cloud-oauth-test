package com.resource.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	@RequestMapping(value="/student/findStudentInfo.*")
	public String findStudentInfo() throws Exception{
		return "{age:20,name:'student'}";
	}
}
