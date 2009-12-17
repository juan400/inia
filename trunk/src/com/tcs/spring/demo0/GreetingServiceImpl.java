package com.tcs.spring.demo0;

public class GreetingServiceImpl implements GreetingService {

	private String greeting; // Esta será IoC / ID
	
	public GreetingServiceImpl() {}
	
	public GreetingServiceImpl(String greeting) {
		this.greeting = greeting;
	}
	
	public void sayGreeting() {
		System.out.println("prueba Spring: " + greeting);
	}
	
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
}
