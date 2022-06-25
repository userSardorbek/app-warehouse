package com.example.appwarehouse.controller;

import com.example.appwarehouse.pload.InputProductDTO;
import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.service.InputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inputProduct")
public class InputProductController {

    @Autowired
    InputProductService inputProductService;

    @PostMapping("/addInputProduct")
    public Result addInputProduct(@RequestBody InputProductDTO inputProductDTO) {
        Result result = inputProductService.addInputProduct(inputProductDTO);
        return result;
    }

    @PutMapping("/editInputProduct/{inputProductId}")
    public Result editInputProduct(@PathVariable Integer inputProductId,@RequestBody InputProductDTO inputProductDTO) {
        Result result = inputProductService.editInputProduct(inputProductId, inputProductDTO);
        return result;
    }

    @GetMapping("/getAllInputProduct")
    public Result getAllInputProduct(){
        Result result = inputProductService.getInputProduct();
        return result;
    }

    @GetMapping("/getInputProduct/{inputProductId}")
    public Result getInputProduct(@PathVariable Integer inputProductId){
        Result inputProduct = inputProductService.getInputProduct(inputProductId);
        return inputProduct;
    }

    @DeleteMapping("/delete/{inputProductId}")
    public Result deleteInputProduct(@PathVariable Integer inputProductId){
        Result result = inputProductService.deleteInputProduct(inputProductId);
        return result;
    }
}
