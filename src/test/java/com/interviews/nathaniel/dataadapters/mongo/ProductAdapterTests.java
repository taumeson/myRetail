package com.interviews.nathaniel.dataadapters.mongo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;

import java.math.RoundingMode;

import org.bson.Document;
import org.decimal4j.immutable.Decimal2f;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.interviews.nathaniel.models.Price;
import com.interviews.nathaniel.models.Product;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class ProductAdapterTests {

	private Document IDDocument;
	private BasicDBObject productDBO;
	private Document productDocument;
	private Product product;
	
	@Before
	public void setup()
	{
		// reusable ID query document
		IDDocument = new Document();
		IDDocument.append("_id", 1234);
		
				
		// reusable product DB object
		BasicDBObject currentDBO = new BasicDBObject();
		productDBO = new BasicDBObject();
		
		currentDBO.append("value", 100.00).append("currency_code", "USD");
		
		productDBO.append("_id", 1234).append("current_price", currentDBO);
				
		
		// reusable product Document
		Document currentPrice = new Document();
		productDocument = new Document();
		
		currentPrice.append("value", Decimal2f.valueOf(100.00)).append("currency_code", "USD");
		productDocument.append("_id", 1234).append("current_price", currentPrice);
		
		
		// reusable Product
		Price current_price = new Price(Decimal2f.valueOf(100.00), "USD");
		product = new Product(1234, "Test Product", current_price);
		
	}
	
	@Test
	public void canAdaptDBObjectToDocument() {

		// test
		assertThat(ProductAdapter.getDocument(productDBO), samePropertyValuesAs(productDocument));
		
	}
	
	@Test
	public void canAdaptDocumentToQueryByIDDocument() {
		
		// given
		BasicDBObject IDDBDocument = new BasicDBObject("_id", 1234);

		// test
		assertThat(ProductAdapter.getIdDocument(IDDBDocument), samePropertyValuesAs(IDDocument));
		
	}
	
	@Test
	public void canAdaptLongToQueryByIDDocument() {

		// test
		assertThat(ProductAdapter.getIdDocument(1234), samePropertyValuesAs(IDDocument));
				
	}
	
	@Test
	public void canAdaptProductToDBObject() {

		// test
		assertThat(ProductAdapter.getDBObject(product), samePropertyValuesAs(productDBO));	
	}
		
	@Test
	public void canReturnNullDocumentForNull() {

		// test
		assertThat(ProductAdapter.getDocument(null), is(nullValue()));	
	}
	
	@Test
	public void canReturnNullIDDocumentForNull() {
	
		// test
		assertThat(ProductAdapter.getIdDocument(null), is(nullValue()));	
	}	
}
