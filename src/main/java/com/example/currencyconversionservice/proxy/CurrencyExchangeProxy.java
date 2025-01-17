package com.example.currencyconversionservice.proxy;

import com.example.currencyconversionservice.proxy.dto.CurrencyExchangeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange-service")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyExchangeResponse retrieveCurrencyExchangeRate(@PathVariable String from, @PathVariable String to);
}
