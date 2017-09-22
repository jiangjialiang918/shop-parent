package com.zs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.provider.service.DemoService;
import com.zs.shop.order.dto.OrderDTO;
import com.zs.shop.order.service.IOrderService;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private DemoService demoService;
	
	@Autowired
	private IOrderService orderService;
	
	@RequestMapping("/dubboTest")
	public void dubboTest(){
		String result = demoService.sayHello("World");		
	}
	
	@RequestMapping("/dbTest")
	public void dbTest(){
		OrderDTO orderDTO = orderService.getByProductId(812103742);
		System.out.println(orderDTO);
	}
	
	
}
