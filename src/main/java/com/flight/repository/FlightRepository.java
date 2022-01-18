package com.flight.repository;

import com.flight.model.entity.Flight;
import java.sql.Timestamp;
import java.util.List;


public interface FlightRepository {

    Flight selectFlightById(Long id);

    List<Flight> selectFlightByName(String name);

    List<Flight> selectFlightByTime(String s_date);

    List<Flight> selectFlightByRoute(String departure, String destination);

    List<Flight> selectAllFlights();

    Integer selectCount();

    Integer insertFlight(Flight flight);

    Integer updateFlightOnNameById(Flight flight);

    Integer deleteFlightById(Long id);

    List<Flight> selectFlightsByPage(Integer offset, Integer page_size);

}
