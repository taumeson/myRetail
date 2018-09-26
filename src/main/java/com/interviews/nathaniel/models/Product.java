/**
 * 
 */
package com.interviews.nathaniel.models;

import org.decimal4j.immutable.Decimal2f;

import com.interviews.nathaniel.communication.RedskyComms;
import com.interviews.nathaniel.interfaces.IPriceRepository;
import com.interviews.nathaniel.repositories.mongo.PriceRepository;

/**
 * @author Nathaniel Engelsen
 *
 */
public class Product {

    private long id;
    private String name;
    private Price current_price;
    private IPriceRepository priceRepository;

    public Product(long id, String name, Price current_price) {
        this.id = id;
        this.current_price = current_price;
        this.setup();
    }

    public Product()
    {
    	this.id = -1;
    	this.current_price = new Price(Decimal2f.valueOf(0), "UNK");
    }
    
    public Product(IPriceRepository priceRepository, long id) {
    	this.id = id;
    	this.updateCurrent_price(priceRepository);
    	this.setup();
    }

    private void setup()
    {
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
    	Price p = priceRepository.getPrice(this);
    	if (p != null)
    	{
    		this.current_price = p;
    	}
    }
}
