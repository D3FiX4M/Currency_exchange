package com.practice.currency_exchange.controller;

import com.practice.currency_exchange.dto.ExchangeRateDto;
import com.practice.currency_exchange.dto.ExchangeResponse;
import com.practice.currency_exchange.service.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/exchange_rate")
public class ExchangeRateController {

    private final ExchangeRateService service;

    @GetMapping("/All")
    public ResponseEntity<List<ExchangeRateDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{baseCode}")
    public ResponseEntity<List<ExchangeRateDto>> getAllBase(
            @PathVariable String baseCode
    ) {
        return ResponseEntity.ok(service.getAllBase(baseCode));
    }

    @GetMapping("/{baseCode}/{targetCode}")
    public ResponseEntity<ExchangeRateDto> get(
            @PathVariable String baseCode,
            @PathVariable String targetCode
    ) {
        return ResponseEntity.ok(service.get(baseCode, targetCode));
    }

    @PostMapping
    public ResponseEntity<ExchangeRateDto> create(
            @RequestBody ExchangeRateDto dto
    ) {
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{baseCode}/{targetCode}")
    public ResponseEntity<ExchangeRateDto> update(
            @PathVariable String baseCode,
            @PathVariable String targetCode,
            @RequestBody Double rate
    ) {
        return ResponseEntity.ok(service.update(baseCode, targetCode, rate));
    }

    @DeleteMapping("/{baseCode}/{targetCode}")
    public ResponseEntity<String> delete(
            @PathVariable String baseCode,
            @PathVariable String targetCode
    ) {
        return ResponseEntity.ok(service.delete(baseCode, targetCode));
    }

    @GetMapping("/{baseCode}/{targetCode}/amount")
    public ResponseEntity<ExchangeResponse> calculate(
            @PathVariable String baseCode,
            @PathVariable String targetCode,
            @RequestParam Long amount
    ) {
        return ResponseEntity.ok(service.calculate(baseCode, targetCode, amount));
    }

}
