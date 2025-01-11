package com.example.currencyconversionservice.controller.dto;

import com.example.currencyconversionservice.proxy.dto.CurrencyExchangeResponse;

import java.math.BigDecimal;

public class CurrencyConversionResponse {

    private Long id;
    private String from;
    private String to;
    private BigDecimal rate;
    private BigDecimal quantity;
    private BigDecimal totalCalculatedAmount;
    private String environment;

    public CurrencyConversionResponse() {
    }

    public CurrencyConversionResponse(Long id, String from, String to,
                                      BigDecimal rate, BigDecimal quantity, BigDecimal totalCalculatedAmount,
                                      String environment) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.rate = rate;
        this.quantity = quantity;
        this.totalCalculatedAmount = totalCalculatedAmount;
        this.environment = environment;
    }

    public CurrencyConversionResponse(final CurrencyExchangeResponse currencyExchangeResponse,
                                      final BigDecimal quantity, final String environment) {
        this.id = currencyExchangeResponse.getId();
        this.from = currencyExchangeResponse.getFrom();
        this.to = currencyExchangeResponse.getTo();
        this.rate = currencyExchangeResponse.getRate();

        this.environment = environment;
        this.quantity = quantity;
        // TODO extract into service or other place for handling business logic
        this.totalCalculatedAmount = rate.multiply(quantity);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalCalculatedAmount() {
        return totalCalculatedAmount;
    }

    public void setTotalCalculatedAmount(BigDecimal totalCalculatedAmount) {
        this.totalCalculatedAmount = totalCalculatedAmount;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
