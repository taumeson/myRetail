package com.interviews.nathaniel.dataadapters.mongo;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;

import org.bson.Document;
import org.decimal4j.immutable.Decimal2f;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.interviews.nathaniel.models.Price;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class PriceAdapterTests {

	private Price mockPrice;
	private Price defaultPrice;
	
	@Before
	public void setup()
	{	
		// setup values
		Decimal2f value = Decimal2f.valueOf(100.00);
		String currency = "USD";
		
		// create mock price
		mockPrice = new Price(value, currency);
		
		// setup values
		value = Decimal2f.valueOf(0.00);
		currency = "UNK";
		
		// create mock price
		defaultPrice = new Price(value, currency);
				
	}
	
	@Test
	public void canAdaptDocumentToPrice() {
		Document priceDocument = new Document();
		Document currentPrice = new Document();
		
		currentPrice.append("value", Decimal2f.valueOf(100.00)).append("currency_code", "USD");
		priceDocument.append("current_price", currentPrice);

		// test
		assertThat(PriceAdapter.getPrice(priceDocument), samePropertyValuesAs(mockPrice));
	}
	
	@Test
	public void canAdaptObjectsToPrice() {

		// test
		assertThat(PriceAdapter.getPrice(100.00, "USD"), samePropertyValuesAs(mockPrice));
	}

	@Test
	public void canReturnDefaultForNullDocument() {
		// test
		assertThat(PriceAdapter.getPrice(null), samePropertyValuesAs(defaultPrice));
	}
	

	@Test
	public void canReturnDefaultForNullValues() {
		// test
		assertThat(PriceAdapter.getPrice(null, "x"), samePropertyValuesAs(defaultPrice));

		assertThat(PriceAdapter.getPrice("x", null), samePropertyValuesAs(defaultPrice));
	}
	
}
