package com.practice.currency_exchange.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeResponse {

    private String baseCurrencyCode;
    private String targetCurrencyCode;
    private double convertedAmount;
}
