package com.practice.currency_exchange.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateDto {
    private String baseCurrencyCode;
    private String targetCurrencyCode;
    private Double rate;
}
