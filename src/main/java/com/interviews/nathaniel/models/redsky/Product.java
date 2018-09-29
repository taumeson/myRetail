package com.interviews.nathaniel.models.redsky;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/********
* 
* @author Nathaniel Engelsen
* @version 1.0
* Date 09/26/2018
* Purpose POJO for Product. DTO for Redsky API
*
/****/
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// we only (de)serialize item, which is a nested value;
	@JsonProperty("item")
	private Item item;
	
	public Product()
	{
		this.item = new Item();
	}

	@JsonProperty("item")
	public Item getItem()
	{
		return item;
	}
}
