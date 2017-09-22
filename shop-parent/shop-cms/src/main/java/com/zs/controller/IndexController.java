package com.zs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {
	

	@RequestMapping("/index")
	public String index(){
		return "index/index";
	}

	@RequestMapping("/{page}")
	//@ResponseBody
	public String showPage(@PathVariable String page){  
		return page;
	}
	
	
}
