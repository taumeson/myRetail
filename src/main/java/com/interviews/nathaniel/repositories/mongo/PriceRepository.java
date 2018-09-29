package com.interviews.nathaniel.repositories.mongo;

import com.interviews.nathaniel.models.Price;
import com.interviews.nathaniel.models.Product;
import com.interviews.nathaniel.dataadapters.mongo.PriceAdapter;
import com.interviews.nathaniel.dataadapters.mongo.ProductAdapter;
import com.interviews.nathaniel.interfaces.IPriceRepository;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/********
* 
* @author Nathaniel Engelsen
* @version 1.0
* Date 09/26/2018
* Purpose Mongodb-based repository class for price manipulations
* Implements IPriceRepository
*
/****/
@Component
public class PriceRepository implements IPriceRepository {
	
	// we'll use priceCollection a few places in our respository, so we will move it to a private variable
	private MongoCollection priceCollection; 
	protected Logger logger = LoggerFactory.getLogger(this.getClass()); // implement logging
	
	public PriceRepository()
	{
		logger.info("Instantiating an instance of a default price repository");
		// this is a POC, so we're leaving mongo with its defaults and without security
		logger.info("Connecting with mongo data store");
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		
		// we'll use a database called "products"
		logger.info("Using/creating database for products");
		MongoDatabase database = mongoClient.getDatabase("products");
		
		// we'll create a collection of documents called "prices"
		logger.info("Switching to the prices collection");
		priceCollection = database.getCollection("prices");

	}
	
	public boolean savePrice(Product product)
	{
		try
		{
			logger.info("Converting product %s to mongo db object", product.getId());
			// convert the product to a MongoDB object that we will save to the collection
			DBObject mongoProduct = ProductAdapter.getDBObject(product);
		
			// we'll upsert the prices
			UpdateOptions options = new UpdateOptions();
			options.upsert(true);
			
			// upsert prices
			logger.info("Performing upsert");
			priceCollection.replaceOne(ProductAdapter.getIdDocument(mongoProduct), ProductAdapter.getDocument(mongoProduct), options );
			
			logger.info("Successfully updated price");
			return true;
			
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
			return false;	
		}
	}

	public Price getPrice(Product product) {
		
		try
		{
			// retrieve a query document
			Document priceDocument = (Document)priceCollection.find(ProductAdapter.getIdDocument(product.getId())).first();

			// retrieve a Price POJO from the returned mongodb document.
			return PriceAdapter.getPrice(priceDocument);
		}
		catch(Exception e)
		{
			logger.error("Unexpected error", e);
			return Price.Default();			
		}
	}
}
