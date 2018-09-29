package com.interviews.nathaniel.models;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;

import org.decimal4j.immutable.Decimal2f;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class PriceModelTests {

	@Test
	public void doesPriceModelReturnCorrectDefault() {
		
		// setup values
		Decimal2f value = Decimal2f.valueOf(0.00);
		String currency = "UNK";
		
		// create mock price
		Price mockPrice = new Price(value, currency);

		// test
		assertThat(Price.Default(), samePropertyValuesAs(mockPrice));
	}
}
