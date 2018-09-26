package com.interviews.nathaniel.dataadapters.mongo;

import org.bson.Document;
import org.decimal4j.immutable.Decimal2f;

import com.interviews.nathaniel.models.Price;

public class PriceAdapter {

	public static Price getPrice(Document priceDocument)
	{
		if (priceDocument == null) return new Price(Decimal2f.valueOf(0), "UNK");
		
		Document current_price = (Document)priceDocument.get("current_price");
		return getPrice(current_price.get("value"), current_price.get("currency_code"));
	}
	
	public static Price getPrice(Object price, Object currency_code)
	{
		if (price == null || currency_code == null) return new Price(Decimal2f.valueOf(0), "UNK");
		
		String s_currency_code = String.valueOf(currency_code);
		Decimal2f d_price = Decimal2f.valueOf(String.valueOf(price));
		return new Price(d_price, s_currency_code);
	}
}
