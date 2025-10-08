package com.example.reservationudemy.services.impl;

import com.example.reservationudemy.entities.BusRoute;
import com.example.reservationudemy.entities.BusSchedule;
import com.example.reservationudemy.models.ReservationApiException;
import com.example.reservationudemy.repos.BusRouteRepository;
import com.example.reservationudemy.repos.BusScheduleRepository;
import com.example.reservationudemy.services.BusScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusScheduleServiceImpl implements BusScheduleService {

    @Autowired
    private BusScheduleRepository busScheduleRepository;

    @Autowired
    private BusRouteRepository busRouteRepository;

    @Override
    public BusSchedule addSchedule(BusSchedule busSchedule) throws ReservationApiException{
        final boolean exists = busScheduleRepository.existsByBusAndBusRouteAndDepartureTime(
                busSchedule.getBus(),
                busSchedule.getBusRoute(),
                busSchedule.getDepartureTime()
        );
        if(exists){
            throw new ReservationApiException(HttpStatus.CONFLICT,"Duplicate Schedule");
        }
        return busScheduleRepository.save(busSchedule);
    }

    @Override
    public List<BusSchedule> getAllBusSchedule() {
        return busScheduleRepository.findAll();
    }

    @Override
    public List<BusSchedule> getSchedulesByRoute(String routeName) {
        final BusRoute busRoute = busRouteRepository.findByRouteName(routeName)
                .orElseThrow(()->new ReservationApiException(HttpStatus.BAD_REQUEST,"Not Found"));
        return busScheduleRepository.findByBusRoute(busRoute)
                .orElseThrow(()->new ReservationApiException(HttpStatus.BAD_REQUEST,"Not Found"));
    }
}
