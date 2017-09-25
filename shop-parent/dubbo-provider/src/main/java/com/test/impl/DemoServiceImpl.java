package com.test.impl;

import com.provider.service.DemoService;

public class DemoServiceImpl implements DemoService {

	@Override
	public String sayHello(String name) {
		System.out.println(name);
		return name;
	}

}
