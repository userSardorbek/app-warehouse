package com.example.appwarehouse.controller;

import com.example.appwarehouse.entity.Currency;
import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @PostMapping("/addCurrency")
    public Result addNewCurrency(@RequestBody Currency currency) {
        Result result = currencyService.addCurrency(currency);
        return result;
    }

    @PutMapping("/editCurrency/{currencyId}")
    public Result editCurrency(@PathVariable Integer currencyId, @RequestBody Currency currency) {
        Result result = currencyService.editCurrency(currencyId, currency);
        return result;
    }

    @GetMapping("/getAllCurrencies")
    public Result getAllCurrencies() {
        Result allCurrencies = currencyService.getAllCurrencies();
        return allCurrencies;
    }

    @GetMapping("/getCurrency/{currencyId}")
    public Result getCurrency(@PathVariable Integer currencyId) {
        Result currency = currencyService.getCurrency(currencyId);
        return currency;
    }

    @DeleteMapping("/deleteCurrency/{currencyId}")
    public Result deleteCurrency(@PathVariable Integer currencyId){
        Result result = currencyService.deleteCurrency(currencyId);
        return result;
    }



}
