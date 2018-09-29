package com.interviews.nathaniel.models;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;

import org.decimal4j.immutable.Decimal2f;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.interviews.nathaniel.factories.PriceFactory;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class ProductModelTests {

	@Test
	public void canCreateProduct() {
		
		// test default controller
		Product product = null;
		try
		{
			product = new Product();
		}
		catch(Exception e)
		{
			Assert.fail("Product unable to be created");
		}
		
		// test various setters
		try
		{
			Price current_price = new Price(Decimal2f.valueOf(100.00), "USD");
			product.setCurrent_price(current_price);
		}
		catch(Exception e)
		{
			Assert.fail("Cannot set price");
		}
				
		try
		{
			product.setName("Test Value");
		}
		catch(Exception e)
		{
			Assert.fail("Cannot set name");
		}
		
		assertThat(product.getName(), is("Test Value"));
		
		try
		{
			product.setId(1234);
		}
		catch(Exception e)
		{
			Assert.fail("Cannot set id");
		}
		
	}
}
