package com.interviews.nathaniel.models.redsky;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item implements Serializable {

	/**
	 * 
	 */
	private static long serialVersionUID = 1L;
	private String tcin;

	@JsonProperty("product_description")
	private Product_Description product_description;
	
	public Item()
	{
		this.product_description = new Product_Description();
		this.tcin = "";
	}
	
	public Item(Product_Description product_description)
	{
		this.product_description = product_description;
		this.tcin = "";
	}
	
	@JsonProperty("product_description")
	public Product_Description getProduct_Description()
	{
		return product_description;
	}
	
	@JsonProperty("tcin")
	public String getTcin()
	{
		return tcin;
	}
}
