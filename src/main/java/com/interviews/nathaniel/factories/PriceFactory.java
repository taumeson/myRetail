package com.interviews.nathaniel.factories;

import org.decimal4j.immutable.Decimal2f;

import com.interviews.nathaniel.models.Price;

public class PriceFactory {

	public static Price Create(Decimal2f value, String currency_code) {
		return new Price(value, currency_code);
	}
}
