package com.example.appwarehouse.controller;

import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.pload.SupplierDTO;
import com.example.appwarehouse.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    @PostMapping("/addSupplier")
    public Result addSupplier(@RequestBody SupplierDTO supplierDTO) {
        Result result = supplierService.addSupplier(supplierDTO);
        return result;
    }

    @PutMapping("/editSupplier/{supplierId}")
    public Result editSupplier(@PathVariable Integer supplierId, @RequestBody SupplierDTO supplierDTO) {
        return supplierService.editSupplier(supplierId, supplierDTO);
    }

    @GetMapping("/getAllSuppliers")
    public Result getAllSuppliers() {
        Result allSuppliers = supplierService.getAllSuppliers();
        return allSuppliers;
    }

    @GetMapping("/getSupplier/{supplierId}")
    public Result getSupplier(@PathVariable Integer supplierId) {
        Result supplier = supplierService.getSupplier(supplierId);
        return supplier;
    }

    @DeleteMapping("/deleteSupplier/{supplierId}")
    public Result deleteSupplier(@PathVariable Integer supplierId) {
        Result result = supplierService.deleteSupplier(supplierId);
        return result;

    }

}