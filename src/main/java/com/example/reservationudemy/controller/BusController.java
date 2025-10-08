package com.example.reservationudemy.controller;
import com.example.reservationudemy.entities.Bus;
import com.example.reservationudemy.models.ResponseModel;
import com.example.reservationudemy.services.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bus")
public class BusController {
    @Autowired
    private BusService busService;

    @PostMapping("/add")
    public ResponseModel<Bus> addBus(@RequestBody Bus bus){
        final Bus savedBus = busService.addBus(bus);
        return new ResponseModel<>(HttpStatus.OK.value(), "Bus Saved",savedBus);
    }

    // wrapping List<bus> with ResponseEntity (inbuilt method of http)
    @GetMapping("/all")
    public ResponseEntity<List<Bus> > getAllBus(){
        return ResponseEntity.ok(busService.getAllBus());
    }
}
