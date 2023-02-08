package com.practice.currency_exchange.service.Impl;

import com.practice.currency_exchange.Exception.ExistingException;
import com.practice.currency_exchange.Exception.NotFoundException;
import com.practice.currency_exchange.dto.ExchangeRateDto;
import com.practice.currency_exchange.dto.ExchangeResponse;
import com.practice.currency_exchange.entity.Currency;
import com.practice.currency_exchange.entity.ExchangeRate;
import com.practice.currency_exchange.mapper.ExchangeRateMapper;
import com.practice.currency_exchange.repository.CurrencyRepository;
import com.practice.currency_exchange.repository.ExchangeRateRepository;
import com.practice.currency_exchange.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository repository;
    private final CurrencyRepository currencyRepository;
    private final ExchangeRateMapper mapper;

    public Currency findByCode(String code) {
        return currencyRepository.findByCode(code)
                .orElseThrow(
                        () -> new NotFoundException("CURRENCY NOT FOUND")
                );
    }

    public ExchangeRate findByKeys(String baseCode, String targetCode) {

        if (!repository.existsByBaseCurrency_CodeAndTargetCurrency_Code(baseCode,targetCode)){
            throw new NotFoundException("EXCHANGE RATE NOT FOUND");
        }
        return repository.findByBaseCurrency_CodeAndTargetCurrency_Code(baseCode, targetCode)
                .orElseThrow(
                        () -> new NotFoundException("EXCHANGE RATE NOT FOUND")
                ).get(0);
    }


    @Override
    public List<ExchangeRateDto> getAllBase(String baseCode) {
        return mapper.toDto(
                repository.findByBaseCurrency_Code(baseCode)
                        .orElseThrow(
                                () -> new NotFoundException("EXCHANGE RATES NOT FOUND")
                        )
        );
    }

    @Override
    public ExchangeRateDto get(String baseCode, String targetCode) {

        return mapper.toDto(findByKeys(baseCode, targetCode));
    }

    @Override
    public List<ExchangeRateDto> getAll() {
        return mapper.toDto(repository.findAll());
    }

    @Override
    public ExchangeRateDto create(ExchangeRateDto dto) {

        if (repository.existsByBaseCurrency_CodeAndTargetCurrency_Code(
                dto.getBaseCurrencyCode(),
                dto.getTargetCurrencyCode())
        ){
            throw new ExistingException("EXCHANGE RATE ALREADY USED");
        }

            ExchangeRate entity = ExchangeRate.builder()
                    .id(null)
                    .baseCurrency(findByCode(dto.getBaseCurrencyCode()))
                    .targetCurrency(findByCode(dto.getTargetCurrencyCode()))
                    .rate(dto.getRate())
                    .build();

        return mapper.toDto(repository.save(entity));
    }

    @Override
    public ExchangeRateDto update(String baseCode, String targetCode, Double rate) {
        ExchangeRate entity = findByKeys(baseCode, targetCode);
        entity.setRate(rate);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public String delete(String baseCode, String targetCode) {
        ExchangeRate entity = findByKeys(baseCode, targetCode);
        repository.delete(entity);
        return "Exchange Rate " +
                entity.getBaseCurrency().getCode() +
                entity.getTargetCurrency().getCode() +
                "deleted";
    }

    public ExchangeResponse calculate(String baseCode, String targetCode, Long amount){
        ExchangeRate entity = findByKeys(baseCode, targetCode);
        return ExchangeResponse.builder()
                .baseCurrencyCode(baseCode)
                .targetCurrencyCode(targetCode)
                .convertedAmount(amount*entity.getRate())
                .build();
    }
}
