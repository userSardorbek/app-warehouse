package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Client;
import com.example.appwarehouse.entity.Currency;
import com.example.appwarehouse.entity.Output;
import com.example.appwarehouse.entity.Warehouse;
import com.example.appwarehouse.pload.OutputDTO;
import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.repository.ClientRepository;
import com.example.appwarehouse.repository.CurrencyRepository;
import com.example.appwarehouse.repository.OutputRepository;
import com.example.appwarehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CurrencyRepository currencyRepository;

    public Result addOutput(OutputDTO outputDTO) {
        Output output = new Output();


        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDTO.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Such warehouse not found", false);
        output.setWarehouse(optionalWarehouse.get());


        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDTO.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Such currency not found", false);
        output.setCurrency(optionalCurrency.get());


        Optional<Client> optionalClient = clientRepository.findById(outputDTO.getClientId());
        if (!optionalClient.isPresent())
            return new Result("Such client not found", false);
        output.setClient(optionalClient.get());


        output.setFactureNumber(outputDTO.getFactureNumber());

        boolean b = outputRepository.existsByCode(output.getCode());
        if (b)
            return new Result("this code was already entered change it", false);
        output.setCode(outputDTO.getCode());

        outputRepository.save(output);
        return new Result("Successfully added", true, output);

    }

    public Result editOutput(Integer outputId, OutputDTO outputDTO) {
        Optional<Output> optionalOutput = outputRepository.findById(outputId);
        if (!optionalOutput.isPresent())
            return new Result("Such output not found", false);
        Output output = optionalOutput.get();

        output.setFactureNumber(outputDTO.getFactureNumber());
        output.setCode(outputDTO.getCode());

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDTO.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new Result("Such warehouse not found", false);
        output.setWarehouse(optionalWarehouse.get());

        Optional<Client> optionalClient = clientRepository.findById(outputDTO.getClientId());
        if (!optionalClient.isPresent())
            return new Result("Such client not found", false);
        output.setClient(optionalClient.get());

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDTO.getCurrencyId());
        if (!optionalCurrency.isPresent())
            return new Result("Such currency not found", false);
        output.setCurrency(optionalCurrency.get());

        outputRepository.save(output);
        return new Result("Successfully edited", true, output);
    }

    public Result getAllOutput() {
        List<Output> all = outputRepository.findAll();
        return new Result("Here is all output", true, all);
    }

    public Result getOutput(Integer outputId) {

        Optional<Output> optionalOutput = outputRepository.findById(outputId);
        if (!optionalOutput.isPresent())
            return new Result("Such output not found", false);
        Output output = optionalOutput.get();
        return new Result("Here is info about the output", true, output);
    }

    public Result deleteOutput(Integer outputId) {
        outputRepository.deleteById(outputId);
        return new Result("Successfully deleted", true);
    }

}
