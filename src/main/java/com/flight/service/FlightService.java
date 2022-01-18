package com.flight.service;

import com.flight.model.entity.Flight;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface FlightService {

    Optional<Flight> getFlightById(Long id);
    List<Flight> getFlightsByName(String name);
    List<Flight> getFlightsByTime(String s_String);
    List<Flight> getFlightsByRoute(String departure, String destination);
    List<Flight> getTotalFlight();
    List<Flight> getFlightsByPage(Integer page, Integer page_size);
    Integer getTotalPage(Integer page_size);
    boolean saveFlight(Flight flight);
    boolean modifyFlightOnNameById(Flight flight);
    boolean deleteFlightById(Long id);



}
