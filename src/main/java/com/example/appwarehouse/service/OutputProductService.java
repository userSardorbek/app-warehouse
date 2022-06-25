package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Output;
import com.example.appwarehouse.entity.OutputProduct;
import com.example.appwarehouse.entity.Product;
import com.example.appwarehouse.pload.OutputProductDTO;
import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.repository.OutputProductRepository;
import com.example.appwarehouse.repository.OutputRepository;
import com.example.appwarehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {

    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OutputRepository outputRepository;

    public Result addOutputProduct(OutputProductDTO outputProductDTO) {
        OutputProduct outputProduct = new OutputProduct();
        if (outputProductDTO.getAmount() == null)
            return new Result("enter the amount", false);
        outputProduct.setAmount(outputProductDTO.getAmount());
        outputProduct.setPrice(outputProductDTO.getPrice());

        Optional<Product> optionalProduct = productRepository.findById(outputProductDTO.getProductId());
        if ( !optionalProduct.isPresent())
            return new Result("Such product not found", false);
        outputProduct.setProduct(optionalProduct.get());

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDTO.getOutputId());
        if ( !optionalOutput.isPresent())
            return new Result("Such output not found", false);
        outputProduct.setOutput(optionalOutput.get());

        outputProductRepository.save(outputProduct);
        return new Result("Successfully added", true, outputProduct);
    }

    public Result editOutputProduct(Integer outputProductId, OutputProductDTO outputProductDTO){

        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(outputProductId);
        if ( !optionalOutputProduct.isPresent())
            return new Result("Such product not found", false);
        OutputProduct outputProduct = optionalOutputProduct.get();

        Optional<Output> optionalOutput = outputRepository.findById(outputProductDTO.getOutputId());
        if ( !optionalOutput.isPresent())
            return new Result("Such output not found", false);
        outputProduct.setOutput(optionalOutput.get());


        Optional<Product> optionalProduct = productRepository.findById(outputProductDTO.getProductId());
        if ( !optionalProduct.isPresent())
            return new Result("Such product not found", false);
        outputProduct.setProduct(optionalProduct.get());

        if (outputProductDTO.getAmount() == null)
            return new Result("enter the amount", false);
        outputProduct.setAmount(outputProductDTO.getAmount());
        outputProduct.setPrice(outputProductDTO.getPrice());

        outputProductRepository.save(outputProduct);
        return new Result("Successfully added", true, outputProduct);

    }

    public Result getAllOutputProduct(){
        List<OutputProduct> all = outputProductRepository.findAll();
        return new Result("Here is info about all outputs", true, all);
    }

    public Result getOutputProduct(Integer outputProductId){
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(outputProductId);
        if ( !optionalOutputProduct.isPresent())
            return new Result("Such output product not found", false);

        return new Result("Successfully added", true, optionalOutputProduct.get());
    }

    public Result deleteOutputProduct(Integer outputProductId){
        outputProductRepository.deleteById(outputProductId);
        return new Result("output product successfully deleted", true);
    }
}
