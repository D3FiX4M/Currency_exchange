package com.practice.currency_exchange.service;

import com.practice.currency_exchange.dto.ExchangeRateDto;
import com.practice.currency_exchange.dto.ExchangeResponse;

import java.util.List;

public interface ExchangeRateService {

    List<ExchangeRateDto> getAllBase(String baseCode);

    List<ExchangeRateDto> getAll();

    ExchangeRateDto get(String baseCode, String targetCode);

    ExchangeRateDto create(ExchangeRateDto dto);

    ExchangeRateDto update(String baseCode, String targetCode, Double rate);

    String delete(String baseCode, String targetCode);

    ExchangeResponse calculate(String baseCode, String targetCode, Long amount);
}
