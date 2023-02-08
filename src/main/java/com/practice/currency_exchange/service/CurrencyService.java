package com.practice.currency_exchange.service;

import com.practice.currency_exchange.dto.CurrencyDto;
import com.practice.currency_exchange.entity.Currency;

import java.util.List;

public interface CurrencyService {

    List<CurrencyDto> getAll();
    CurrencyDto get(String code);
    CurrencyDto create(CurrencyDto dto);
    List<CurrencyDto> create(List<CurrencyDto> dtoList);
    CurrencyDto update(String code, CurrencyDto dto);

    String delete(String code);

}
