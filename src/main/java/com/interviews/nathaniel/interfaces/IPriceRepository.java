package com.interviews.nathaniel.interfaces;

import com.interviews.nathaniel.models.Price;
import com.interviews.nathaniel.models.Product;

public interface IPriceRepository {
	public boolean savePrice(Product product);
	public Price getPrice(Product product);
}
