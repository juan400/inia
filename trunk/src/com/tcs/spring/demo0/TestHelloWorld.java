package com.tcs.spring.demo0;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestHelloWorld {
	private static ApplicationContext applicationContext;
	
	public static void main(String[] args) throws Exception {
		
		applicationContext = new FileSystemXmlApplicationContext("c:/application-context.xml");
	
		GreetingService service = (GreetingService)applicationContext.getBean("greetingService");
		
		service.sayGreeting();
	}

}
