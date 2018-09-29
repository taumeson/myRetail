package com.interviews.nathaniel.dataadapters.mongo;

import java.math.RoundingMode;

import org.bson.Document;

import com.interviews.nathaniel.models.Product;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/********
* 
* @author Nathaniel Engelsen
* @version 1.0
* Date 09/26/2018
* Purpose Product Adapter to convert between POJO classes and MongoDB classes
*
/****/
public class ProductAdapter {

	public static DBObject getDBObject(Product product)
	{
		// converting our POJO to a mongo db object requires some manipulation
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
	
	// we return a document with just the id of the object that we can use for querying
	// TODO make this a property of a possible adapter superclass
	public static Document getIdDocument(DBObject doc)
	{
		if(doc == null) return null;
		return new Document("_id", doc.get("_id"));
	}
	
	
	// we return a document with just the id passed in. this should definitely not get implemented
	// with every single future adapter. 
	// TODO make this a property of a possible adapter superclass
	public static Document getIdDocument(long id)
	{
		return new Document("_id", id);
	}
}
