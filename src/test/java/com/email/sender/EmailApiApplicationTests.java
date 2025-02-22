package com.email.sender;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.email.sender.service.EmailService;

@SpringBootTest
class EmailApiApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
	private EmailService service;
	public void sendEmail() {
		System.out.print("sending email");
		service.sendEmail("test@gmail.com","Email from Springboot", "HELLO WORLD");
		
	}

}
