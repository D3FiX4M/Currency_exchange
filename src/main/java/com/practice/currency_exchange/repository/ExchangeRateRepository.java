package com.practice.currency_exchange.repository;

import com.practice.currency_exchange.entity.Currency;
import com.practice.currency_exchange.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    Optional<List<ExchangeRate>> findByBaseCurrency_Code(String code);
    Optional<List<ExchangeRate>> findByBaseCurrency_CodeAndTargetCurrency_Code(String baseCode, String targetCode);

    boolean existsByBaseCurrency_CodeAndTargetCurrency_Code(String baseCode, String targetCode);

}
