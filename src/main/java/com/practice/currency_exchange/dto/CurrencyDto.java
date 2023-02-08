package com.practice.currency_exchange.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDto {

    private String code;
    private String name;
    private String sign;

}
