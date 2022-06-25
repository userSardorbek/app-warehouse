package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Currency;
import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    public Result addCurrency(Currency currency) {
        Optional<Currency> byName = currencyRepository.findByName(currency.getName());
        if (!byName.isPresent()) {
            currencyRepository.save(currency);
            return new Result("Successfully edited", true);
        }
        return new Result("Such currency exist", false);
    }

    public Result editCurrency(Integer currencyId, Currency currency) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(currencyId);
        if (!optionalCurrency.isPresent())
            return new Result("Such warehouse not found", false);

        Currency changing = optionalCurrency.get();

        changing.setName(currency.getName());
        changing.setActive(currency.isActive());

        currencyRepository.save(changing);
        return new Result("Successfully edited", true);
    }

    public Result getAllCurrencies() {
        List<Currency> all = currencyRepository.findAll();
        return new Result("All currencies", true, all);
    }

    public Result getCurrency(Integer currencyId) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(currencyId);
        if (!optionalCurrency.isPresent())
            return new Result("Such currency not found", false);
        Currency currency = optionalCurrency.get();
        return new Result("Information about your search currency", true, currency);
    }

    public Result deleteCurrency(Integer currencyId) {
        currencyRepository.deleteById(currencyId);
        return new Result("Successfully deleted", true);
    }
}
