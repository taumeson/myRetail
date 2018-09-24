package com.interviews.nathaniel.models.redsky;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RedskyResponse implements Serializable {
	/**
	 * 
	 */
	private static long serialVersionUID = 1L;
	private Product product;
	
	public RedskyResponse()
	{
		this.product = new Product();
	}
	
	public RedskyResponse(Product product)
	{
		this.product = product;
	}
	
	@JsonProperty("product")
	public Product getProduct()
	{
		return product;
	}
}
