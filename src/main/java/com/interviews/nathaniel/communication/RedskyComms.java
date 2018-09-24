/**
 * 
 */
package com.interviews.nathaniel.communication;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interviews.nathaniel.models.redsky.Product;
import com.interviews.nathaniel.models.redsky.RedskyResponse;

/**
 * @author Nathaniel Engelsen
 *
 */
public class RedskyComms {

	private static String urlTemplate = "https://redsky.target.com/v2/pdp/tcin/%s?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";
	
	public static String GetProductName(long id)
	{
		String url = String.format(urlTemplate, id);
		String title = "No Title Information Available";
		
		RestTemplate restTemplate = new RestTemplate();
		RedskyResponse redskyResponse;
		try {
			redskyResponse = restTemplate.getForObject(url, RedskyResponse.class);
			title = redskyResponse.getProduct().getItem().getProduct_Description().getTitle();
		} catch (HttpClientErrorException e) {
			switch(e.getRawStatusCode())
			{
				case 404:
					title = "Product Not Found";
					break;
				default:
					title = "No Title Information Available";
					break;
			}
		}
		
		/*String result = restTemplate.getForObject(url, String.class);
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			JsonNode nodes = mapper.readTree(result);
			nodes.isArray();
			JsonNode product = nodes.get("product");
			JsonNode item = product.get("item");
			JsonNode product_description = item.get("product_description");
			title = product_description.get("title").asText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}*/

		return title;
        
	}
}
