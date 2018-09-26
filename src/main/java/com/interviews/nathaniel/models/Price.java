package com.interviews.nathaniel.models;

import org.decimal4j.immutable.Decimal2f;


/********
* 
* @author Nathaniel Engelsen
* @version 1.0
* Date 09/26/2018
* Purpose POJO for our price collection
*
/****/
public class Price {

    private final Decimal2f value;
    private final String currency_code; // TODO, use an enumeration for acceptable currency codes

    public Price(Decimal2f value, String currency_code) {
        this.value = value;
        this.currency_code = currency_code;
    }

    public Decimal2f getValue() {
        return value;
    }    

    public String getCurrency_code() {
        return currency_code;
    }
    
    public static Price Default()
    {
    	return new Price(Decimal2f.valueOf(0), "UNK"); // our default price values 
    }
}
