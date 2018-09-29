package com.interviews.nathaniel.models.redsky;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/********
* 
* @author Nathaniel Engelsen
* @version 1.0
* Date 09/26/2018
* Purpose POJO for Items. DTO used for Redsky API
*
/****/
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// we only (de)serialize description, which is a nested value
	@JsonProperty("product_description")
	private Product_Description product_description; 
	
	public Item()
	{
		this.product_description = new Product_Description();
	}
	
	@JsonProperty("product_description")
	public Product_Description getProduct_Description()
	{
		return product_description;
	}
}
