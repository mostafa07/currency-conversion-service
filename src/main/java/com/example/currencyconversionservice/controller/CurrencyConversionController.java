package com.example.currencyconversionservice.controller;

import com.example.currencyconversionservice.controller.dto.CurrencyConversionResponse;
import com.example.currencyconversionservice.proxy.CurrencyExchangeProxy;
import com.example.currencyconversionservice.proxy.dto.CurrencyExchangeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;


    @GetMapping("currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionResponse retrieveCurrencyConversion(@PathVariable("from") String from,
                                                                 @PathVariable("to") String to,
                                                                 @PathVariable("quantity") BigDecimal quantity) {
        final HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        final ResponseEntity<CurrencyExchangeResponse> response =
                new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                        CurrencyExchangeResponse.class, uriVariables);
        final CurrencyExchangeResponse currencyExchangeResponse = response.getBody();

        return new CurrencyConversionResponse(currencyExchangeResponse, quantity,
                currencyExchangeResponse.getEnvironment() + " rest template");
    }

    @GetMapping("currency-conversion/feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionResponse retrieveCurrencyConversionUsingFeign(@PathVariable("from") String from,
                                                                           @PathVariable("to") String to,
                                                                           @PathVariable("quantity") BigDecimal quantity) {
        final CurrencyExchangeResponse currencyExchangeResponse =
                currencyExchangeProxy.retrieveCurrencyExchangeRate(from, to);

        return new CurrencyConversionResponse(currencyExchangeResponse, quantity,
                currencyExchangeResponse.getEnvironment() + " feign");
    }
}
