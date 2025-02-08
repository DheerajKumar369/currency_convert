package com.myproject.currency_converter.service;

import java.util.Map;

public interface ExchangeRateService {

    double convertCurrency(String from, String to, double amount);

    Map<String, Double> getCurrencyExchangeRates(String base);

}
