/**
 * 
 */
package com.interviews.nathaniel.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.interviews.nathaniel.communication.RedskyComms;
import com.interviews.nathaniel.models.Product;
import com.interviews.nathaniel.repositories.mongo.PriceRepository;

/**
 * @author Nathaniel Engelsen
 *
 */
@RestController
public class ProductController {

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Product product(@PathVariable long id) {
        return new Product(id);
    }
    
    @RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String product(@PathVariable long id, @RequestBody Product product) {
    	PriceRepository.savePrice(product);
    	return "testing";
    }
}
