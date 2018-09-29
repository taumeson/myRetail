package com.interviews.nathaniel.repositories.mongo;

import com.interviews.nathaniel.models.Price;
import com.interviews.nathaniel.models.Product;
import com.interviews.nathaniel.dataadapters.mongo.PriceAdapter;
import com.interviews.nathaniel.dataadapters.mongo.ProductAdapter;
import com.interviews.nathaniel.interfaces.IPriceRepository;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;

import org.bson.Document;
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
	
	public PriceRepository()
	{
		// this is a POC, so we're leaving mongo with its defaults and without security
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		
		// we'll use a database called "products"
		MongoDatabase database = mongoClient.getDatabase("products");
		
		// we'll create a collection of documents called "prices"
		priceCollection = database.getCollection("prices");

	}
	
	public boolean savePrice(Product product)
	{
		// convert the product to a MongoDB object that we will save to the collection
		DBObject mongoProduct = ProductAdapter.getDBObject(product);

		// we'll upsert the prices
		UpdateOptions options = new UpdateOptions();
		options.upsert(true);
		
		// upsert prices
		priceCollection.replaceOne(ProductAdapter.getIdDocument(mongoProduct), ProductAdapter.getDocument(mongoProduct), options );
		
		return true;
	}

	public Price getPrice(Product product) {
		
		// retrieve a query document
		Document priceDocument = (Document)priceCollection.find(ProductAdapter.getIdDocument(product.getId())).first();

		// retrieve a Price POJO from the returned mongodb document.
		return PriceAdapter.getPrice(priceDocument);
	}
}
