package com.interviews.nathaniel;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.interviews.nathaniel.controllers.ProductController;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class ProductapiApplicationTests {

    @Autowired
    private ProductController controller;

	@Test
	public void contextLoads() {
		assertThat(controller, is(notNullValue()));
	}

}
