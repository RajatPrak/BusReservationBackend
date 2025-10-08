package com.example.reservationudemy.controller;

import com.example.reservationudemy.entities.Reservation;
import com.example.reservationudemy.models.ResponseModel;
import com.example.reservationudemy.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/add")
    public ResponseModel<Reservation> addReservation(@RequestBody Reservation reservation){
        final Reservation res = reservationService.addReservation(reservation);
        return new ResponseModel<>(HttpStatus.OK.value(),"Reservation Saved",res);
    }

   @GetMapping("/all")
    public ResponseEntity<List<Reservation>>getAllReservation(){
        return ResponseEntity.ok(reservationService.getAllReservation());
   }

   @GetMapping("/query")
    public ResponseEntity<List<Reservation>> getReservationByScheduleAndDepartureDate(
            @RequestParam Long scheduleId,
            @RequestParam String departureDate
   ){
        return ResponseEntity.ok(reservationService.getReservationByScheduleAndDepartureDate(scheduleId,departureDate));
   }

   @GetMapping("/all/{mobile}")
    public ResponseEntity<List<Reservation>> getReservationByMobile(@PathVariable(name = "mobile")String mobile){
        return ResponseEntity.ok(reservationService.getReservationByMobile(mobile));
   }
}
