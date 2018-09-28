/**
 * 
 */
package com.interviews.nathaniel.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.interviews.nathaniel.communication.RedskyComms;
import com.interviews.nathaniel.factories.ProductFactory;
import com.interviews.nathaniel.interfaces.IPriceRepository;
import com.interviews.nathaniel.models.Product;
import com.interviews.nathaniel.repositories.mongo.PriceRepository;

/********
* 
* @author Nathaniel Engelsen
* @version 1.0
* Date 09/26/2018
* Purpose Controller for /product API endpoint. Implements HATEOAS pattern.
*
/****/
@RestController
public class ProductController {

	private IPriceRepository PriceRepository; // abstract data layer from controller
	
	public ProductController(IPriceRepository priceRepository)
	{
		PriceRepository = priceRepository; // dependency injection. we love spring.
	}
	
    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET) // we don't need to specify GET
    @ResponseBody // we include values in the response body
    public ResponseEntity<Product> product(@PathVariable long id) {
    	Product product = ProductFactory.Create(PriceRepository, id);
    	HttpStatus responseStatus = HttpStatus.OK; // by default we'll respond 200 for GET
    	
    	// i dislike not having output variables. not worth creating a custom return class
    	switch(product.getName()) 
    	{
		    case "Product Not Found":
		    	responseStatus = HttpStatus.NOT_FOUND; // 404
	    		break;
	    	case "No Title Information Available":
	    		responseStatus = HttpStatus.INTERNAL_SERVER_ERROR; // 500
	    		break;
	    }
    	
    	return new ResponseEntity<Product>(product, responseStatus);
    }
    
    @RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
    @ResponseBody // we include values in the response body
    public ResponseEntity<Product> product(@PathVariable long id, @RequestBody Product product) {
    
    	HttpStatus responseStatus = HttpStatus.OK; // by default we'll respond 200 for PUT

    	// we will return "not modified" if we couldn't save the price
    	responseStatus = PriceRepository.savePrice(product) ? HttpStatus.OK : HttpStatus.NOT_MODIFIED; 
   	
    	return new ResponseEntity<Product>(ProductFactory.Create(PriceRepository, id), responseStatus); // we response 200 for PUT
    }
}
