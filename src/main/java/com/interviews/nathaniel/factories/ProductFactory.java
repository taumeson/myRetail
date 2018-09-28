package com.interviews.nathaniel.factories;

import com.interviews.nathaniel.communication.RedskyComms;
import com.interviews.nathaniel.interfaces.IPriceRepository;
import com.interviews.nathaniel.models.Price;
import com.interviews.nathaniel.models.Product;

public class ProductFactory {
    private IPriceRepository priceRepository;

    private static String getRedskyName(Product p)
    {
    	// update the name of the product from the API during instantiation
    	// TODO store this in a product data store
    	return  RedskyComms.GetProductName(p.getId());
    }

    private static Price getCurrent_price(IPriceRepository priceRepository, Product p)
    {
    	// update the price from the price data store
    	return priceRepository.getPrice(p);
    }
    
    public static Product Create(long id, String name, Price current_price) {
        Product p = new Product();
    	
        p.setId(id);
        p.setCurrent_price(current_price);
        p.setName(name);
        
        return p;
    }

    public static Product Create()
    {
    	Product p = new Product();
    	
    	p.setId(-1);
    	p.setCurrent_price(Price.Default());
    	
    	return p;
    }
    
    public static Product Create(IPriceRepository priceRepository, long id) {
    	Product p = new Product();
    	
    	p.setId(id);    	
    	
    	// a primary requirement is to update the name of the product from the Redsky API
    	p.setName(ProductFactory.getRedskyName(p));
    	
    	if (p.getName() == "Product Not Found" || p.getName() == "No Title Information Available")
    	{
    		p.setCurrent_price(Price.Default());
    	}
    	else
    	{
        	// a primary requirement is to keep the price up to date in our price data store
        	Price price = ProductFactory.getCurrent_price(priceRepository, p);
        	
        	if (price != null)
        	   	p.setCurrent_price(price);
    		
    	}
    	
    	return p;
    }
}
