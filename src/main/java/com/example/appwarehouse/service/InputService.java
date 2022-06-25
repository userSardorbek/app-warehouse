package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Currency;
import com.example.appwarehouse.entity.Input;
import com.example.appwarehouse.entity.Supplier;
import com.example.appwarehouse.entity.Warehouse;
import com.example.appwarehouse.pload.InputDTO;
import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.repository.CurrencyRepository;
import com.example.appwarehouse.repository.InputRepository;
import com.example.appwarehouse.repository.SupplierRepository;
import com.example.appwarehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CurrencyRepository currencyRepository;


    public Result addInput(InputDTO inputDTO) {
        Input input = new Input();

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDTO.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Such warehouse not found", false);
        input.setWarehouse(optionalWarehouse.get());

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDTO.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("Such supplier not found", false);
        input.setSupplier(optionalSupplier.get());

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDTO.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Such currency not found", false);
        input.setCurrency(optionalCurrency.get());


        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        input.setDate(timestamp);
        input.setFactureNumber(inputDTO.getFactureNumber());
        if (inputDTO.getCode()==null)
            return new Result("Null code not allowed", false);
        input.setCode(inputDTO.getCode());



        inputRepository.save(input);
        return new Result("Successfully added", true, input);
    }

    public Result editInput(Integer inputId, InputDTO inputDTO) {

        Optional<Input> optionalInput = inputRepository.findById(inputId);
        if (!optionalInput.isPresent())
            return new Result("Such input not found", false);
        Input input = optionalInput.get();

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDTO.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Such warehouse not found", false);
        input.setWarehouse(optionalWarehouse.get());

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDTO.getSupplierId());
        if (!optionalSupplier.isPresent())
            return new Result("Such supplier not found", false);
        input.setSupplier(optionalSupplier.get());

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDTO.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Such currency not found", false);
        input.setCurrency(optionalCurrency.get());


        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        input.setDate(timestamp);

        input.setFactureNumber(inputDTO.getFactureNumber());

        if (inputDTO.getCode()==null)
            return new Result("Null code not allowed", false);
        input.setCode(inputDTO.getCode());

        Input editedInput = inputRepository.save(input);

        return new Result("Successfully edited", true, editedInput);

    }

    public Result getAllInputs() {
        List<Input> all = inputRepository.findAll();
        return new Result("here is info about all Inputs", true);
    }

    public Result getInput(Integer inputId) {
        Optional<Input> optionalInput = inputRepository.findById(inputId);
        if (!optionalInput.isPresent())
            return new Result("not found", false);
        Input input = optionalInput.get();
        return new Result("Here is info about input id: " + inputId, true, input);
    }

    public Result deleteInput(Integer inputId) {
        inputRepository.deleteById(inputId);
        return new Result("Successfully added", true);
    }
}
