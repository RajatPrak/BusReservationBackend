package com.example.reservationudemy.repos;

import com.example.reservationudemy.entities.BusSchedule;
import com.example.reservationudemy.entities.Customer;
import com.example.reservationudemy.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    Optional<List<Reservation>> findByCustomer(Customer customer);
    Optional<List<Reservation>> findByBusScheduleAndDepartureDate(BusSchedule busSchedule, String departureDate);
}
