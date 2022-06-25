package com.example.appwarehouse.controller;

import com.example.appwarehouse.pload.InputDTO;
import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/input")
public class InputController {

    @Autowired
    InputService inputService;

    @PostMapping("/addInput")
    public Result addInput(@RequestBody InputDTO inputDTO) {
        return inputService.addInput(inputDTO);
    }

    @PutMapping("/editInput/{inputId}")
    public Result editInput(@PathVariable Integer inputId, @RequestBody InputDTO inputDTO) {
        return inputService.editInput(inputId, inputDTO);
    }

    @GetMapping("/getAllInputs")
    public Result getAllInputs() {
        return inputService.getAllInputs();
    }

    @GetMapping("/getInput/{inputId}")
    public Result getInput(@PathVariable Integer inputId) {
        return inputService.getInput(inputId);
    }

    @DeleteMapping("deleteInput/{inputId}")
    public Result deleteInput(@PathVariable Integer inputId){
        return inputService.deleteInput(inputId);
    }

}
