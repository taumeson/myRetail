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

@Component
public class PriceRepository implements IPriceRepository {
	
	private MongoCollection priceCollection;
	
	public PriceRepository()
	{
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		MongoDatabase database = mongoClient.getDatabase("products");
		priceCollection = database.getCollection("prices");

	}
	
	public boolean savePrice(Product product)
	{
		DBObject mongoProduct = ProductAdapter.toDBObject(product);

		UpdateOptions options = new UpdateOptions();
		options.upsert(true);
		
		priceCollection.replaceOne(ProductAdapter.getIdDocument(mongoProduct), ProductAdapter.getDocument(mongoProduct), options	);
		
		return true;
	}

	public Price getPrice(Product product) {
		Document priceDocument = (Document)priceCollection.find(ProductAdapter.getIdDocument(product.getId())).first();

		return PriceAdapter.getPrice(priceDocument);
	}
}
