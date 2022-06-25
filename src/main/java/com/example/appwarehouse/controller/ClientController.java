package com.example.appwarehouse.controller;

import com.example.appwarehouse.pload.ClientDTO;
import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService service;

    @PostMapping("/addClient")
    public Result addClient(@RequestBody ClientDTO clientDTO) {
        Result result = service.addClient(clientDTO);
        return result;
    }

    @PutMapping("/editClient{clientId}")
    public Result editClient(@PathVariable Integer clientId, @RequestBody ClientDTO clientDTO) {
        Result result = service.editClient(clientId, clientDTO);
        return result;
    }

    @GetMapping("/getClient{clientId}")
    public Result getClient(@PathVariable Integer clientId) {
        return service.getClient(clientId);
    }

    @GetMapping("/getAllClients")
    public Result getAllClients() {
        return service.getClients();
    }

    @DeleteMapping("/delete/{clientId}")
    public Result deleteClient(@PathVariable Integer clientId) {
        return service.deleteClient(clientId);
    }
}
