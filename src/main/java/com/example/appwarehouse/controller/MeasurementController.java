package com.example.appwarehouse.controller;

import com.example.appwarehouse.entity.Measurement;
import com.example.appwarehouse.pload.Result;
import com.example.appwarehouse.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    @Autowired
    MeasurementService measurementService;

    @PostMapping
    public Result addMeasurementController(@RequestBody Measurement measurement){
        Result result = measurementService.addMeasurementService(measurement);
        return result;
    }

    @GetMapping
    public List<Measurement> getAllMeasurements(){
        return measurementService.readMeasurement();
    }

    @PutMapping("/{measurementId}")
    public Result editMeasurement(@PathVariable Integer measurementId, @RequestBody Measurement measurement){
        Result result = measurementService.editMeasurement(measurementId, measurement);
        return result;
    }

    @DeleteMapping("/{measurementId}")
    public Result deleteMeasurement(@PathVariable Integer measurementId){
        Result result = measurementService.deleteMeasurement(measurementId);
        return result;
    }
}
