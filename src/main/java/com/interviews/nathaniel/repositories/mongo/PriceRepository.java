package com.interviews.nathaniel.repositories.mongo;

import com.interviews.nathaniel.models.Product;
import com.interviews.nathaniel.dataadapters.mongo.ProductAdapter;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;

import org.bson.Document;
import org.decimal4j.immutable.Decimal2f;

public class PriceRepository {
	
	public static boolean savePrice(Product product)
	{
		MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
		MongoDatabase database = mongoClient.getDatabase("products");
		MongoCollection collection = database.getCollection("prices");

		DBObject mongoProduct = ProductAdapter.toDBObject(product);

		UpdateOptions options = new UpdateOptions();
		options.upsert(true);
		
		collection.replaceOne(ProductAdapter.getIdDocument(mongoProduct), ProductAdapter.getDocument(mongoProduct), options	);
		
		return true;
	}
}
