package com.interviews.nathaniel.models.redsky;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product_Description implements Serializable {
    /**
	 * 
	 */
	private static long serialVersionUID = 1L;
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
