package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Input;
import com.example.appwarehouse.entity.InputProduct;
import com.example.appwarehouse.entity.Product;
import com.example.appwarehouse.pload.InputProductDTO;
import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.repository.InputProductRepository;
import com.example.appwarehouse.repository.InputRepository;
import com.example.appwarehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {

    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputRepository inputRepository;

    public Result addInputProduct(InputProductDTO inputProductDTO) {

        InputProduct inputProduct = new InputProduct();

        inputProduct.setAmount(inputProductDTO.getAmount());
        inputProduct.setPrice(inputProductDTO.getPrice());

        Date date = new Date();
        inputProduct.setExpireDate(date);

        Optional<Product> optionalProduct = productRepository.findById(inputProductDTO.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Such product not found", false);
        inputProduct.setProduct(optionalProduct.get());

        Optional<Input> optionalInput = inputRepository.findById(inputProductDTO.getInputId());
        if (!optionalInput.isPresent())
            return new Result("Such input not found", false);
        inputProduct.setInput(optionalInput.get());

        inputProductRepository.save(inputProduct);
        return new Result("Successfully added", true, inputProduct);
    }

    public Result editInputProduct(Integer inputProductId, InputProductDTO inputProductDTO) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(inputProductId);
        if (!optionalInputProduct.isPresent())
            return new Result("Such product not found", false);
        InputProduct inputProduct = optionalInputProduct.get();

        Optional<Input> optionalInput = inputRepository.findById(inputProductDTO.getInputId());
        if (!optionalInput.isPresent())
            return new Result("Such input not found", false);
        inputProduct.setInput(optionalInput.get());


        Date date = new Date();
        inputProduct.setExpireDate(date);

        Optional<Product> optionalProduct = productRepository.findById(inputProductDTO.getProductId());
        if (!optionalProduct.isPresent())
            return new Result("Such product not found", false);
        inputProduct.setProduct(optionalProduct.get());


        inputProduct.setAmount(inputProductDTO.getAmount());
        inputProduct.setPrice(inputProductDTO.getPrice());

        inputProductRepository.save(inputProduct);
        return new Result("Successfully edited", true, inputProduct);

    }

    public Result getInputProduct(Integer inputProductId) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(inputProductId);
        if (!optionalInputProduct.isPresent())
            return new Result("Such InputProduct not found", false);
        InputProduct inputProduct = optionalInputProduct.get();
        return new Result("Here is info about inputProduct", true, inputProduct);
    }

    public Result getInputProduct() {
        List<InputProduct> all = inputProductRepository.findAll();
        return new Result("Here is info about all input products", true, all);

    }

    public Result deleteInputProduct(Integer inputProductId) {
        inputProductRepository.deleteById(inputProductId);
        return new Result("Successfully deleted", true);

    }
}
