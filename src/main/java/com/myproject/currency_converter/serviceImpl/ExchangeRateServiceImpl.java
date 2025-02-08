package com.myproject.currency_converter.serviceImpl;


import com.myproject.currency_converter.model.CurrencyApiResponse;
import com.myproject.currency_converter.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Value("${api.url}")
    private String API_URL;

    @Value("${api.key}")
    private String API_KEY;


    private final RestTemplate restTemplate = new RestTemplate();

    public double convertCurrency(String from, String to, double amount) {
        Map<String, Double> rates = getCurrencyExchangeRates(from);
        Double fromRate = rates.get(from);
        Double toRate = rates.get(to);
        return amount * (toRate / fromRate);
    }

    public Map<String, Double> getCurrencyExchangeRates(String baseCurrency) {
        try {
            String url = String.format("%s?access_key=%s", API_URL, API_KEY);

            CurrencyApiResponse response = restTemplate.getForObject(url, CurrencyApiResponse.class);

            Map<String, Double> actualRates = response.getRates();
            Double baseRate = actualRates.get(baseCurrency);

            if (baseRate == null) {
                throw new IllegalArgumentException("Invalid base currency value : " + baseCurrency);
            }

            Map<String, Double> convertedRates = actualRates.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue() / baseRate));
            convertedRates.put(baseCurrency, 1.0);

            return convertedRates;
        } catch (Exception e) {
            throw new RuntimeException("exchange rates fetching failed...", e);
        }
    }




}