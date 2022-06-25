package com.example.appwarehouse.controller;

import com.example.appwarehouse.entity.Warehouse;
import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @PostMapping("/addNewWarehouse")
    public Result addWarehouse(@RequestBody Warehouse warehouse) {
        Result result = warehouseService.addWarehouse(warehouse);
        return result;
    }

    @PutMapping("/editWarehouse/{warehouseId}")
    public Result editWarehouse(@PathVariable Integer warehouseId, @RequestBody Warehouse warehouse) {
        Result result = warehouseService.editWarehouse(warehouseId, warehouse);
        return result;
    }

    //Get all warehouses
    @GetMapping("/getAllWarehouse")
    public Result allWarehouses() {
        Result result = warehouseService.allWarehouses();
        return result;
    }

    //get a warehouse
    @GetMapping("/getWarehouse/{warehouseId}")
    public Result getWarehouse(@PathVariable Integer warehouseId) {
        Result warehouse = warehouseService.getWarehouse(warehouseId);
        return warehouse;
    }

    //DELETE
    @DeleteMapping("/deleteWarehouse/{warehouseId}")
    public Result deleteWarehouse(@PathVariable Integer warehouseId) {
        Result result = warehouseService.deleteWarehouse(warehouseId);
        return result;
    }


}
