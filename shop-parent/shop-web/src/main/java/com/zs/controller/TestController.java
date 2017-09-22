package com.zs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.provider.service.DemoService;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private DemoService demoService;
	
	
	
	@RequestMapping("/dubboTest")
	public void dubboTest(){
		String result = demoService.sayHello("World");		
	}
	
	
	
	
}
