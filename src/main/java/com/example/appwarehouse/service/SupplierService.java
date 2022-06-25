package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Supplier;
import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.pload.SupplierDTO;
import com.example.appwarehouse.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public Result addSupplier(SupplierDTO supplierDTO) {
        Supplier supplier = new Supplier();
        boolean exists = supplierRepository.existsByName(supplierDTO.getName());
        if (exists)
            return new Result("change name please", false);
        supplier.setName(supplierDTO.getName());
        supplier.setPhoneNumber(supplierDTO.getPhoneNumber());

        supplierRepository.save(supplier);
        return new Result("Successfully edited", true);
    }

    public Result editSupplier(Integer supplierId, SupplierDTO supplierDTO) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);
        if (!optionalSupplier.isPresent())
            return new Result("Such supplier not found", false);
        Supplier supplier = optionalSupplier.get();
        supplier.setName(supplierDTO.getName());
        supplier.setActive(supplierDTO.getActive());
        supplier.setPhoneNumber(supplierDTO.getPhoneNumber());

        supplierRepository.save(supplier);
        return new Result("Successfully saved", true);
    }

    public Result getAllSuppliers() {
        List<Supplier> all = supplierRepository.findAll();
        return new Result("Here is info about all suppliers", true, all);
    }

    public Result getSupplier(Integer supplierId) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);
        if (!optionalSupplier.isPresent())
            return new Result("The supplier not found", false);
        Supplier supplier = optionalSupplier.get();
        return new Result("Here is info about the supplier", true, supplier);
    }

    public Result deleteSupplier(Integer supplierId){
        supplierRepository.deleteById(supplierId);
        return new Result("Supplier deleted", true);
    }

}
