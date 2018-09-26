package com.interviews.nathaniel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/********
* 
* @author Nathaniel Engelsen
* @version 1.0
* Date 09/26/2018
* Purpose Entry point for myRetail API
*
/****/

@SpringBootApplication
public class ProductapiApplication {

	public static void main(String[] args) {
		// spool up application context and set up dependency injection
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	
		// execute main app using SpringBoot
		SpringApplication.run(ProductapiApplication.class, args);
	}
}
