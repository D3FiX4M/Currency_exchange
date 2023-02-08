package com.practice.currency_exchange.service.Impl;

import com.practice.currency_exchange.Exception.ExistingException;
import com.practice.currency_exchange.Exception.NotFoundException;
import com.practice.currency_exchange.dto.CurrencyDto;
import com.practice.currency_exchange.entity.Currency;
import com.practice.currency_exchange.mapper.CurrencyMapper;
import com.practice.currency_exchange.repository.CurrencyRepository;
import com.practice.currency_exchange.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyMapper mapper;
    private final CurrencyRepository repository;

    @Override
    public List<CurrencyDto> getAll() {
        return mapper.toDto(repository.findAll()
        );
    }


    public Currency findByCode(String code){
        return repository.findByCode(code)
                .orElseThrow(
                        () -> new NotFoundException("CURRENCY NOT FOUND")
                );
    }

    @Override
    public CurrencyDto get(String code) {
        return mapper.toDto(
                findByCode(code)
        );
    }

    @Override
    public CurrencyDto create(CurrencyDto dto) {

        if (repository.existsByCode(dto.getCode())){
            throw new ExistingException("CODE ALREADY USED");
        }
        return mapper.toDto(
                repository.save(
                        mapper.toEntity(dto)
                )
        );
    }

    @Override
    public List<CurrencyDto> create(List<CurrencyDto> dtoList) {

        for (CurrencyDto dto: dtoList
             ) {
            if (repository.existsByCode(dto.getCode())){
                throw new ExistingException("CODE ALREADY USED");
            }
        }

        return mapper.toDto(
                repository.saveAll(
                        mapper.toEntity(dtoList)
                )
        );
    }

    @Override
    public CurrencyDto update(String code, CurrencyDto dto) {

        if (repository.existsByCode(code)){
            throw new ExistingException("CODE ALREADY USED");
        }

        Currency entity = findByCode(code);
        mapper.update(entity, dto);
        return mapper.toDto(
                repository.save(entity)
        );
    }

    @Override
    public String delete(String code) {

        if (!repository.existsByCode(code)){
            throw new NotFoundException("CODE NOT FOUND");
        }

        Currency currency = findByCode(code);
        repository.delete(currency);
        return "Currency" + currency.getName() + " deleted";
    }

}
