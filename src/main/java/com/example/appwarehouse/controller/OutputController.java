package com.example.appwarehouse.controller;

import com.example.appwarehouse.pload.OutputDTO;
import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.service.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/output")
public class OutputController {

    @Autowired
    OutputService outputService;

    @PostMapping("/addOutput")
    public Result addOutput(@RequestBody OutputDTO outputDTO) {
        Result result = outputService.addOutput(outputDTO);
        return result;
    }

    @PutMapping("/editOutput/{outputId}")
    public Result editOutput(@PathVariable Integer outputId, @RequestBody OutputDTO outputDTO){
        return outputService.editOutput(outputId, outputDTO);
    }

    @GetMapping("/getAllOutput")
    public Result getAllOutput(){
        return outputService.getAllOutput();
    }

    @GetMapping("/getOutput/{outputId}")
    public Result getOutput(@PathVariable Integer outputId){
        Result output = outputService.getOutput(outputId);
        return output;
    }

    @DeleteMapping("/deleteOutput/{outputId}")
    public Result deleteOutput(@PathVariable Integer outputId){
        return outputService.deleteOutput(outputId);
    }


}
