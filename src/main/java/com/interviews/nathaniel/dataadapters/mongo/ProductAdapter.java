package com.interviews.nathaniel.dataadapters.mongo;

import java.math.RoundingMode;

import org.bson.Document;

import com.interviews.nathaniel.models.Product;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class ProductAdapter {

	public static DBObject toDBObject(Product product)
	{
	    return new BasicDBObject("_id", product.getId())
                .append("current_price", new BasicDBObject("currency_code", product.getCurrent_price().getCurrency_code())
                                             .append("value", product.getCurrent_price().getValue().floatValue(RoundingMode.FLOOR))
                                             );
	}
	
	public static Document getDocument(DBObject doc)
	{
	   if(doc == null) return null;
	   return new Document(doc.toMap());
	}
	
	public static Document getIdDocument(DBObject doc)
	{
		if(doc == null) return null;
		return new Document("_id", doc.get("_id"));
	}
	
	public static Document getIdDocument(long id)
	{
		return new Document("_id", id);
	}
}
