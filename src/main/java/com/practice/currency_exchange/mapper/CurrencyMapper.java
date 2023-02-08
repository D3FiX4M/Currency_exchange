package com.practice.currency_exchange.mapper;

import com.practice.currency_exchange.dto.CurrencyDto;
import com.practice.currency_exchange.entity.Currency;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CurrencyMapper {

    public Currency toEntity(CurrencyDto dto){
        return Currency.builder()
                .id(null)
                .code(dto.getCode())
                .name(dto.getName())
                .sign(dto.getSign())
                .build();
    }

    public List<Currency> toEntity(List<CurrencyDto> dtoList){
        List<Currency> listEntities = new ArrayList<>();
        for (CurrencyDto dto: dtoList
             ) {
            listEntities.add(toEntity(dto));
        }
        return listEntities;
    }

    public CurrencyDto toDto(Currency entity){
        return CurrencyDto.builder()
                .code(entity.getCode())
                .name(entity.getName())
                .sign(entity.getSign())
                .build();
    }

    public List<CurrencyDto> toDto(List<Currency> listEntity){
        List<CurrencyDto> listDto = new ArrayList<>();
        for (Currency entity: listEntity
        ) {
            listDto.add(toDto(entity));
        }
        return listDto;
    }

    public void update(Currency entity, CurrencyDto dto){
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setSign(dto.getSign());
    }

    public void update(List<Currency> entityList, List<CurrencyDto> dtoList){
        if (entityList.size()==dtoList.size()){
            for (int i = 0; i <entityList.size() ; i++) {
                entityList.get(i).setCode(dtoList.get(i).getCode());
                entityList.get(i).setName(dtoList.get(i).getName());
                entityList.get(i).setSign(dtoList.get(i).getSign());
            }
        }
    }
}
