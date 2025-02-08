package com.myproject.currency_converter.controller;
import com.myproject.currency_converter.model.CurrencyConversionRequest;
import com.myproject.currency_converter.model.CurrencyConversionResponse;
import com.myproject.currency_converter.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class CurrencyController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @PostMapping("/convert-currency")
    public ResponseEntity<CurrencyConversionResponse> convertCurrency(@RequestBody CurrencyConversionRequest request) {
        double convertedAmount = exchangeRateService.convertCurrency(request.getFrom(), request.getTo(), request.getAmount());
        CurrencyConversionResponse currencyConversionResponse = new CurrencyConversionResponse(
                request.getFrom(),
                request.getTo(),
                request.getAmount(),
                convertedAmount
        );
        return new ResponseEntity<>(currencyConversionResponse, HttpStatus.OK);
    }

    @GetMapping("/currency-rates")
    public ResponseEntity<Map<String, Double>> getExchangeRates(@RequestParam(value = "base", defaultValue = "USD") String base) {
        Map<String, Double> rates = exchangeRateService.getCurrencyExchangeRates(base);
        return new ResponseEntity<>(rates,HttpStatus.OK);
    }


}