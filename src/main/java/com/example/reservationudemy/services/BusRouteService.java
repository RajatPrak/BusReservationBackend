package com.example.reservationudemy.services;

import com.example.reservationudemy.entities.BusRoute;

import java.util.List;

public interface BusRouteService {
    BusRoute addRoute(BusRoute busRoute);
    List<BusRoute> getAllBusRoutes();
    BusRoute getRouteByRouteName(String routeName);
    BusRoute getRouteByCityFromAndCityTo(String cityFrom, String cityTo);
}
