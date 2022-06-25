package com.example.appwarehouse.controller;

import com.example.appwarehouse.pload.OutputProductDTO;
import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.service.OutputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/outputProductController")
public class OutputProductController {

    @Autowired
    OutputProductService outputProductService;

    @PostMapping("/addOutputProduct")
    public Result addOutputProduct(@RequestBody OutputProductDTO outputProductDTO){
        Result result = outputProductService.addOutputProduct(outputProductDTO);
        return result;
    }

    @PutMapping("/editOutputProduct/{outputProductId}")
    public Result editOutputProduct(@PathVariable Integer outputProductId, @RequestBody OutputProductDTO outputProductDTO){
        Result result = outputProductService.editOutputProduct(outputProductId, outputProductDTO);
        return result;
    }

    @GetMapping("/getAllOutputProducts")
    public Result getAllOutputProducts(){
        Result allOutputProduct = outputProductService.getAllOutputProduct();
        return allOutputProduct;
    }

    @GetMapping("/getOutputProduct/{outputProductId}")
    public Result getOutputProduct(@PathVariable Integer outputProductId){
        Result outputProduct = outputProductService.getOutputProduct(outputProductId);
        return outputProduct;
    }

    @DeleteMapping("deleteOutputProduct/{outputProductId}")
    public Result deleteOutputProduct(@PathVariable Integer outputProductId){
        Result result = outputProductService.deleteOutputProduct(outputProductId);
        return result;
    }
}
