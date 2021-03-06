package com.interviews.nathaniel.factories;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.decimal4j.immutable.Decimal2f;

import static org.hamcrest.Matchers.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.interviews.nathaniel.models.Price;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import com.interviews.nathaniel.factories.PriceFactory;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class PriceFactoryTests {

	@Test
	public void isPriceCreated() {
		
		// setup values
		Decimal2f value = Decimal2f.valueOf(100.00);
		String currency = "USD";
		
		// create mock price
		Price mockPrice = new Price(value, currency);

		// factory test
		Price price = PriceFactory.Create(value, currency);
		
		assertThat(price, samePropertyValuesAs(mockPrice));
	}	
}
