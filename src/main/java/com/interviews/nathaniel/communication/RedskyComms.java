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

/********
* 
* @author Nathaniel Engelsen
* @version 1.0
* Date 09/26/2018
* Purpose Communication channel for Redsky API
*
/****/

public class RedskyComms {

	// for this code demonstration, we reuse the same URL template.
	private static String urlTemplate = "https://redsky.target.com/v2/pdp/tcin/%s?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";
	
	// retrieve product name from the Redsky API
	public static String GetProductName(long id)
	{
		String url = String.format(urlTemplate, id);
		String title = "No Title Information Available";
		
		// utilize spring's built-in tools to access RESTful APIs
		RestTemplate restTemplate = new RestTemplate(); 
		RedskyResponse redskyResponse;
		
		
		try {
			// access redsky restful URL, auto-deserialize into the RedskyResponse wrapper
			redskyResponse = restTemplate.getForObject(url, RedskyResponse.class);
			
			// the only information we are looking for is title
			title = redskyResponse.getProduct().getItem().getProduct_Description().getTitle();
		} catch (HttpClientErrorException e) {
			switch(e.getRawStatusCode())
			{
				case 404:
					// Based on HATEOAS, a 404 is returned if the product is not found.
					title = "Product Not Found"; 
					break;
				default:
					// we'll return a 500 from the controller if there is a different error
					title = "No Title Information Available"; 
					break;
			}
		}

		return title;
        
	}
}
