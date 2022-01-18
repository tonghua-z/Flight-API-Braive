package com.flight.service.impl;

import com.flight.model.entity.Flight;
import com.flight.repository.FlightRepository;
import com.flight.service.FlightService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public Optional<Flight> getFlightById(Long id) {
        return Optional.ofNullable(flightRepository.selectFlightById(id));
    }

    @Override
    public List<Flight> getFlightsByName(String name) {
        return flightRepository.selectFlightByName(name);
    }

    @Override
    public List<Flight> getFlightsByTime(String s_date) {
        return flightRepository.selectFlightByTime(s_date);
    }

    @Override
    public List<Flight> getFlightsByRoute(@Param("departure") String departure,@Param("destination") String destination) {
        return flightRepository.selectFlightByRoute(departure, destination);
    }


    @Override
    public List<Flight> getTotalFlight() {
        return flightRepository.selectAllFlights();
    }

    @Override
    public List<Flight> getFlightsByPage(Integer page, Integer page_size) {
        Integer offset = page < 1 ? 1 : (page - 1) * page_size;
        return flightRepository.selectFlightsByPage(offset, page_size);
    }

    @Override
    public Integer getTotalPage(Integer page_size) {
        Integer flight_count = flightRepository.selectCount();
        return (flight_count % page_size == 0) ? (flight_count / page_size) : (flight_count / page_size + 1);
    }

    @Override
    @Transactional
    public boolean saveFlight(Flight flight) {
        return flightRepository.insertFlight(flight) > 0;
    }

    @Override
    @Transactional
    public boolean modifyFlightOnNameById(Flight flight) {
        return flightRepository.updateFlightOnNameById(flight) > 0;
    }

    @Override
    @Transactional
    public boolean deleteFlightById(Long id) {
        return flightRepository.deleteFlightById(id) > 0;
    }
}
