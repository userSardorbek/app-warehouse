package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Warehouse;
import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addWarehouse(Warehouse warehouse){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findByName(warehouse.getName());
        if (optionalWarehouse.isPresent())
            return new Result("Such named warehouse is already exist", false);

        warehouseRepository.save(warehouse);
        return new Result("Successfully added", true);
    }

    public Result editWarehouse(Integer warehouseId, Warehouse warehouse){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouseId);
        if (!optionalWarehouse.isPresent())
            return new Result("Such warehouse not found", false);

        Warehouse changingWarehouse = optionalWarehouse.get();
        changingWarehouse.setName(warehouse.getName());
        changingWarehouse.setActive(warehouse.isActive());

        warehouseRepository.save(changingWarehouse);
        return new Result ("Successfully edited", true);
    }

    public Result allWarehouses(){
        List<Warehouse> all = warehouseRepository.findAll();
        return new Result("Info about all warehouses", true, all);
    }

    public Result getWarehouse(Integer warehouseId){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouseId);
        if (!optionalWarehouse.isPresent())
            return new Result ("Such warehouse not found", false);
        Warehouse warehouse = optionalWarehouse.get();
        return new Result("Here is info about your warehouse", true, warehouse);
    }

    public Result deleteWarehouse(Integer warehouseId){
        warehouseRepository.deleteById(warehouseId);
        return new Result ("Successfully deleted", true);
    }
}
