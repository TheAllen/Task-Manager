package com.TheAllen.TaskManager.domain;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Project {
	
	@RequestMapping(path="/helloworld", method=RequestMethod.GET)
	public String helloWorld() {
		return "Hello World";
	}
}
