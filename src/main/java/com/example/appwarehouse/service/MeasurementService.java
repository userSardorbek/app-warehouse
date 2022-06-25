package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Measurement;
import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    public Result addMeasurementService(Measurement measurement) {
        boolean existsByName = measurementRepository.existsByName(measurement.getName());
        if (existsByName) {
            return new Result("There is already have such measurement", false);
        }
        measurementRepository.save(measurement);
        return new Result("Successfully saved", true);
    }

    public List<Measurement> readMeasurement(){
        List<Measurement> all = measurementRepository.findAll();
        return all;
    }

    public Result editMeasurement(Integer measurementId, Measurement measurement){

        Optional<Measurement> byId = measurementRepository.findById(measurementId);
        if (!byId.isPresent())
            return new Result("such a unit of measurement does not exist", false);
        Measurement editingMeasurement = byId.get();
        editingMeasurement.setActive(measurement.isActive());
        editingMeasurement.setName(measurement.getName());

        measurementRepository.save(editingMeasurement);
        return new Result("Successfully edited", true);
    }

    public Result deleteMeasurement(Integer id){
        measurementRepository.deleteById(id);
        return new Result("deleted", true);
    }

}
