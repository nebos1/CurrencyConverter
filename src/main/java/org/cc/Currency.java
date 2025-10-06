package org.cc;

public class Currency {
    private String name; // Lev,Euro...
    private String code; // BGN,EUR...
    private double exchange_rate; // from BGN ( 1.00 ) to every other

    public Currency(String name_value, String code_value, double exchange_rate_value) {
        if (name_value == null || name_value.isEmpty()) {
            throw new IllegalArgumentException ("Error! Illegal name!");
        }
        if (code_value == null || code_value.isEmpty()) {
            throw new IllegalArgumentException("Error! Illegal currency code!");
        }
        if (exchange_rate_value < 0) {
            throw new IllegalArgumentException("Error! Negative currency!");
        }
        this.name = name_value;
        this.code = code_value.toUpperCase();
        this.exchange_rate = exchange_rate_value;
    }

    // getters
    public String getName() {
        return name;
    }
    public  String getCode() {
        return code;
    }
    public double getExchange_rate() {
        return exchange_rate;
    }

}
