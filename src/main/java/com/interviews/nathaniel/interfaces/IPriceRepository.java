package com.interviews.nathaniel.interfaces;

import com.interviews.nathaniel.models.Price;
import com.interviews.nathaniel.models.Product;

/********
* 
* @author Nathaniel Engelsen
* @version 1.0
* Date 09/26/2018
* Purpose Interface for price repositories
*
/****/
public interface IPriceRepository {
	public boolean savePrice(Product product); // save prices to our data store
	public Price getPrice(Product product); // get the saved price from our data store
}
