package com.practice.currency_exchange.mapper;

import com.practice.currency_exchange.dto.ExchangeRateDto;
import com.practice.currency_exchange.entity.ExchangeRate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ExchangeRateMapper {


    public ExchangeRate toEntity(ExchangeRateDto dto){
        return ExchangeRate.builder()
                .id(null)
                .baseCurrency(null)
                .targetCurrency(null)
                .rate(dto.getRate())
                .build();
    }

    public List<ExchangeRate> toEntity(List<ExchangeRateDto> dtoList){
        List<ExchangeRate> entityList = new ArrayList<>();
        for (ExchangeRateDto dto: dtoList
             ) {
            entityList.add(toEntity(dto));
        }
        return entityList;
    }

    public ExchangeRateDto toDto(ExchangeRate entity){
        return ExchangeRateDto.builder()
                .baseCurrencyCode(entity.getBaseCurrency().getCode())
                .targetCurrencyCode(entity.getTargetCurrency().getCode())
                .rate(entity.getRate())
                .build();
    }

    public List<ExchangeRateDto> toDto(List<ExchangeRate> entityList){
        List<ExchangeRateDto> dtoList = new ArrayList<>();
        for (ExchangeRate entity: entityList
             ) {
            dtoList.add(toDto(entity));
        }
        return dtoList;
    }

}
