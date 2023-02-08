package com.practice.currency_exchange.controller;

import com.practice.currency_exchange.dto.CurrencyDto;
import com.practice.currency_exchange.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Currency")
public class CurrencyController {

    private final CurrencyService service;

    @GetMapping("/{code}")
    public ResponseEntity<CurrencyDto> get(@PathVariable String code){
        return ResponseEntity.ok(service.get(code));
    }

    @PostMapping
    public ResponseEntity<CurrencyDto> create(@RequestBody CurrencyDto dto){
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{code}")
    public ResponseEntity<CurrencyDto> update(@PathVariable String code,
            @RequestBody CurrencyDto dto){
        return ResponseEntity.ok(service.update(code, dto));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<String> delete(@PathVariable String code){
        return ResponseEntity.ok(service.delete(code));
    }


    @GetMapping("/all")
    public ResponseEntity<List<CurrencyDto>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/all")
    public ResponseEntity<List<CurrencyDto>> createAll(@RequestBody List<CurrencyDto> dtoList){
        return ResponseEntity.ok(service.create(dtoList));
    }

}
