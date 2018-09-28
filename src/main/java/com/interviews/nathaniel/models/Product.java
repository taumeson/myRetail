/**
 * 
 */
package com.interviews.nathaniel.models;

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

    public void setId(long value)
    {
    	this.id = value;
    }
    
    public void setName(String value)
    {
    	this.name = value;
    }
    
    public void setCurrent_price(Price value)
    {
    	this.current_price = value;
    }
}
