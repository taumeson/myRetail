/**
 * 
 */
package com.interviews.nathaniel.models;

import org.decimal4j.immutable.Decimal2f;

import com.interviews.nathaniel.communication.RedskyComms;

/**
 * @author Nathaniel Engelsen
 *
 */
public class Product {

    private final long id;
    private String name;
    private final Price current_price;

    public Product(long id, String name, Price current_price) {
        this.id = id;
        this.current_price = current_price;
        this.setup();
    }

    public Product(long id) {
    	this.id = id;
    	this.current_price = new Price(Decimal2f.valueOf(12.12),"USD");
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
}
