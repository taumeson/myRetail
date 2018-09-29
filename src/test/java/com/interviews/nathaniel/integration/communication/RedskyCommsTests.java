package com.interviews.nathaniel.integration.communication;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.interviews.nathaniel.communication.RedskyComms;
import com.interviews.nathaniel.models.redsky.RedskyResponse;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class RedskyCommsTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void canRetrieveProducts() {
		String urlTemplate = "https://redsky.target.com/v2/pdp/tcin/13860429";
		
		String request = "{ }";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(request, headers);
		ResponseEntity<String> response = this.restTemplate.exchange(urlTemplate, HttpMethod.GET, entity, String.class);

		assertEquals(200, response.getStatusCodeValue());	
	}
	
	@Test
	public void canGet404IfNoProduct() {
		String urlTemplate = "https://redsky.target.com/v2/pdp/tcin/00000";
		
		String request = "{ }";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(request, headers);
		ResponseEntity<String> response = this.restTemplate.exchange(urlTemplate, HttpMethod.GET, entity, String.class);

		assertEquals(404, response.getStatusCodeValue());	
	}
	
	@Test
	public void isRedskyResponseModelDeserializedCorrectly() {
		String urlTemplate = "https://redsky.target.com/v2/pdp/tcin/13860429";

		RedskyResponse redskyResponse = restTemplate.getForObject(urlTemplate, RedskyResponse.class);
		assertThat(redskyResponse.getProduct().getItem().getProduct_Description().getTitle(), not(isEmptyOrNullString()));
	}	
	
	@Test
	public void doesRedskyCommsClassWork() {
		
		assertThat(RedskyComms.GetProductName(13860429), is("SpongeBob SquarePants: SpongeBob's Frozen Face-off"));
	}	
	
	@Test
	public void doesRedskyCommsClassThrow404s() {
		
		assertThat(RedskyComms.GetProductName(00000), is("Product Not Found"));
	}	
}
