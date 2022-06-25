package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Client;
import com.example.appwarehouse.pload.ClientDTO;
import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    public Result addClient(ClientDTO clientDTO){

        Client client = new Client();
        client.setPhoneNumber(clientDTO.getPhoneNumber());
        client.setName(clientDTO.getName());

        repository.save(client);
        return new Result("Successfully added", true);

    }

    public Result editClient(Integer clientId, ClientDTO clientDTO){
        Optional<Client> optional = repository.findById(clientId);
        if (!optional.isPresent())
            return new Result("Client not found", false);
        Client client = optional.get();
        client.setName(clientDTO.getName());
        client.setPhoneNumber(clientDTO.getPhoneNumber());

        Client save = repository.save(client);
        return new Result ("Successfully edited", true, client);

    }

    public Result getClients(){
        List<Client> all = repository.findAll();
        return new Result("Here is info about all clients", true, all);
    }

    public Result getClient(Integer clientId){
        Optional<Client> optionalClient = repository.findById(clientId);
        if (!optionalClient.isPresent())
            return new Result("Client not found", false);
        Client client = optionalClient.get();

        return new Result("Here is info about the client", true, client);
    }

    public Result deleteClient(Integer clientId){
        repository.deleteById(clientId);
        return new Result ("Successfully deleted", true);
    }

}
