package com.interviews.nathaniel.models;

import org.decimal4j.immutable.Decimal2f;

/**
 * @author Nathaniel Engelsen
 *
 */
public class Price {

    private final Decimal2f value;
    private final String currency_code;

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
}
