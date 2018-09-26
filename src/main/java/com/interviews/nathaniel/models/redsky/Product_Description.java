package com.interviews.nathaniel.models.redsky;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/********
* 
* @author Nathaniel Engelsen
* @version 1.0
* Date 09/26/2018
* Purpose POJO for product description. DTO for Redsky API
*
/****/
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product_Description implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// we only (de)serialize title
	@JsonProperty("title")
	private String title;
    
    public Product_Description(String title)
    {
    	this.title = title;
    }
    
    public Product_Description() {
	}

    @JsonProperty("title")
	public String getTitle() {
        return title;
    }
}
