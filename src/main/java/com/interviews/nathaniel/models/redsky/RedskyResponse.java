package com.interviews.nathaniel.models.redsky;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/********
* 
* @author Nathaniel Engelsen
* @version 1.0
* Date 09/26/2018
* Purpose POJO wrapper for response from Redsky API
*
/****/
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedskyResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// we only (de)serialize product, which is a nested value
	// product is also a top level value but has a document wrapper
	@JsonProperty("product")
	private Product product;
	
	public RedskyResponse()
	{
		this.product = new Product();
	}

	@JsonProperty("product")
	public Product getProduct()
	{
		return product;
	}
}
