package com.example.reservationudemy.services;

import com.example.reservationudemy.entities.Bus;

import java.util.List;

public interface BusService {
    Bus addBus(Bus bus);
    List<Bus> getAllBus();
}
