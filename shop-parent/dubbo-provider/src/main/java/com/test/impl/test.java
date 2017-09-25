package com.test.impl;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class  test {
	public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
        context.start();

        System.out.println(" app run ");

        System.in.read(); // æŒ‰ä»»æ„é”®é€?å‡?
    }
}
