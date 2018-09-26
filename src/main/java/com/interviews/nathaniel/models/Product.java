/**
 * 
 */
package com.interviews.nathaniel.models;

import org.decimal4j.immutable.Decimal2f;

import com.interviews.nathaniel.communication.RedskyComms;
import com.interviews.nathaniel.interfaces.IPriceRepository;
import com.interviews.nathaniel.repositories.mongo.PriceRepository;


/********
* 
* @author Nathaniel Engelsen
* @version 1.0
* Date 09/26/2018
* Purpose POJO representing a product in our inventory
*
/****/
public class Product {

    private long id;
    private String name;
    private Price current_price;
    private IPriceRepository priceRepository;

    public Product(long id, String name, Price current_price) {
        this.id = id;
        this.current_price = current_price;
        this.name = name;
    }

    public Product()
    {
    	this.id = -1;
    	this.current_price = Price.Default();
    }
    
    public Product(IPriceRepository priceRepository, long id) {
    	this.id = id;
    	
    	// a primary requirement is to keep the price up to date in our price data store
    	this.updateCurrent_price(priceRepository); 
    	
    	// a primary requirement is to update the name of the product from the Redsky API
    	this.updateName();
    }

    private void updateName()
    {
    	// update the name of the product from the API during instantiation
    	// TODO store this in a product data store
    	String name = RedskyComms.GetProductName(this.id);
    	this.name = name;
    }
    
	public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public Price getCurrent_price()
    {
    	return current_price;
    }    

    public void updateCurrent_price(IPriceRepository priceRepository)
    {
    	// update the price from the price data store
    	Price p = priceRepository.getPrice(this);
    	if (p != null)
    	{
    		this.current_price = p;
    	}
    }
}
