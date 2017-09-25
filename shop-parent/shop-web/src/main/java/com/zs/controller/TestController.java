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
		System.out.println("----------------------");
	}
	
	
	/**
	 * 
	 * return "index";代表返回jsp文件夹下的index页面，前提是在springmvc-servlet.xml配置
	 * <!-- 视图解析器 -->
	<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/jsp/" />
		<property name="suffix" value=".jsp" />
	</bean>
	 * @author:zhangshuai
	 * @data:2017年9月25日
	 */
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	
	
	
}
