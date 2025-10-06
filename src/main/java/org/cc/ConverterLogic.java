package org.cc;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConverterLogic {
    // all values stored
    private final Map<String, Currency> currencies = new LinkedHashMap<>();

    public ConverterLogic() {
        ExchangeRates();
    }

    // the keys of the values + their exchange rate compared to 1 BGN lev
    private void ExchangeRates () {
        currencies.put("BGN", new Currency("Bulgarian lev", "BGN", 1.00));
        currencies.put("EUR", new Currency("Euro", "EUR", 0.51));
        currencies.put("USD", new Currency("United States dollar", "USD", 0.60));
        currencies.put("GBP", new Currency("Britain lire", "GBP", 0.44));
        currencies.put("TRY", new Currency("Turkish lire", "TRY", 24.92));
        currencies.put("RUB", new Currency("Russian ruble", "RUB", 49.73));
        currencies.put("CNY", new Currency("Chinese yuan", "CNY", 4.26));
        currencies.put("RSD", new Currency("Serbian dinar", "RSD", 59.89));
        currencies.put("MKD", new Currency("Macedonian denar", "MKD", 31.50));
        currencies.put("JPY", new Currency("Japanese yen", "JPY", 89.77));
    }

    private void Add(Currency c) {
        currencies.put(c.getCode(), c);
    }

    public double Convert(double amount, String FromCode, String ToCode) {
        if (amount < 0) {
            throw new IllegalArgumentException("Error! Negative amount!");
        }
        if (FromCode == null || FromCode.isEmpty() || ToCode == null || ToCode.isEmpty()) {
            throw new IllegalArgumentException("Error! Illegal currency code!");
        }
        double RateFrom = getExchangeRate(FromCode);
        double RateTo = getExchangeRate(ToCode);
        return (amount  /  RateFrom) * RateTo;
    }

    private double getExchangeRate(String code) {
        Currency currency = currencies.get(code.toUpperCase());
        if (currency == null) {
            throw new IllegalArgumentException("Error! Currencdy code not found: " + code);
        }
        return currency.getExchange_rate();
    }

    public String[] getCurrencyCodes() {
        return currencies.keySet().toArray(new String[0]);
    }

}
