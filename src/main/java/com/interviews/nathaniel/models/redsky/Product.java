package com.interviews.nathaniel.models.redsky;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable {
	/**
	 * 
	 */
	private static long serialVersionUID = 1L;
	private Item item;
	
	public Product()
	{
		this.item = new Item();
	}
	
	public Product(Item item)
	{
		this.item = item;
	}
	
	@JsonProperty("item")
	public Item getItem()
	{
		return item;
	}
}
