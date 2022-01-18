package com.flight.web.controller;


import com.flight.constant.FlightConstant;
import com.flight.model.dto.PaginatedResult;
import com.flight.model.entity.Flight;
import com.flight.service.FlightService;
import com.flight.web.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/flight")
public class FlightController {

    private FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }



    @GetMapping("/id/{flightId}")
    public ResponseEntity<?> getFlightById(@PathVariable Long flightId) {
        return flightService
                .getFlightById(flightId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException()
                        .setResourceName(FlightConstant.Flight)
                        .setId(flightId));
    }

    @GetMapping("/name/{flightName}")
    public ResponseEntity<?> getFlightByName(@PathVariable String flightName) {

        return ResponseEntity
                .ok(new PaginatedResult()
                        .setData(flightService.getFlightsByName(flightName)));
    }



    @GetMapping("/date/{flightDate}")
    public ResponseEntity<?> getFlightByTime(@PathVariable String flightDate) {

        return ResponseEntity
                .ok(new PaginatedResult()
                        .setData(flightService.getFlightsByTime(flightDate)));
    }

        @GetMapping("/route/{departure}/{destination}")
        public ResponseEntity<?> getFlightByRoute(@PathVariable String departure, @PathVariable String destination) {

            return ResponseEntity
                    .ok(new PaginatedResult()
                            .setData(flightService.getFlightsByRoute(departure, destination)));
        }

    @GetMapping()
    public ResponseEntity<?> getTotalFlight() {

        return ResponseEntity
                .ok(new PaginatedResult()
                        .setData(flightService.getTotalFlight()));
    }

    @GetMapping("/paginated/{page}")
    public ResponseEntity<?> getPaginatedFlights(@PathVariable int page) {
        // Parse request parameters
        int page_size = FlightConstant.PAGE_SIZE;
        int page_num = flightService.getTotalPage(page_size);
        if (page<1 || page>page_num){
            page = 1;
        }

        return ResponseEntity
                .ok(new PaginatedResult()
                        .setData(flightService.getFlightsByPage(page, page_size))
                        .setCurrentPage(page)
                        .setTotalPage(page_num));
    }


    @PostMapping
    public ResponseEntity<?> postFlight(@RequestBody Flight flight) {
        flightService.saveFlight(flight);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(flight.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(flight);

    }

    @PutMapping("/{flightId}")
    public ResponseEntity<?> putFlight(@PathVariable Long flightId, @RequestBody Flight flight) {
        assertFlightExist(flightId);

        flightService.modifyFlightOnNameById(flight.setId(flightId));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(flight);
    }

    @DeleteMapping("/{flightId}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long flightId) {
        assertFlightExist(flightId);

        flightService.deleteFlightById(flightId);

        return ResponseEntity
                .noContent()
                .build();
    }

    /********************************** Existence Check **********************************/
    private void assertFlightExist(Long flightId) {
        flightService
                .getFlightById(flightId)
                .orElseThrow(() -> new ResourceNotFoundException()
                        .setResourceName(FlightConstant.Flight)
                        .setId(flightId));
    }

}
