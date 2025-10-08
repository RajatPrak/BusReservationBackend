package com.example.reservationudemy.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long busId;
//    @Column(name = "bus_name")
    private String busName;
    private String busType;
    private Integer totalSeats;
    @Column(unique = true)
    private String busNumber;
}
