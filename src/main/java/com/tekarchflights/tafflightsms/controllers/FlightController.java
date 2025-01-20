package com.tekarchflights.tafflightsms.controllers;


import com.tekarchflights.tafflightsms.models.Flights;
import com.tekarchflights.tafflightsms.services.FlightServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
@AllArgsConstructor
public class FlightController {

    private final FlightServiceImp flightService;


    @PostMapping
    public Flights addFlight(@RequestBody Flights flight) {
        return flightService.addFlight(flight);
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<Object> getUserById(@PathVariable("flightId") Long id) {
        return flightService.getFlightById(id);
    }

    @GetMapping
    public List<Flights> getAllFlights() {
        return flightService.getAllFlights();
    }


    @PutMapping("/{flightId}")
    public ResponseEntity<String> updateUser(@PathVariable("flightId") Long id, @RequestBody Flights flight) {

        ResponseEntity<Object> flightDetails = flightService.getFlightById(id);

        if (flightDetails.getStatusCode().is2xxSuccessful()) {
            try {
                flightService.updateFlightDetails(id, flight);
                return ResponseEntity.ok("Flight Details updated successfully");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to update flight details");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight with id " + id + " not found");
        }
    }


    @DeleteMapping("/{flightId}")
    public ResponseEntity<String> deleteFlight(@PathVariable("flightId") Long id) {

        ResponseEntity<Object> flightDetails = flightService.getFlightById(id);

        if (flightDetails.getStatusCode().is2xxSuccessful()) {
            try {
                flightService.deleteFlight(id);
                return ResponseEntity.ok("Flight deleted successfully");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to delete flight ");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight with id " + id + " not found");
        }

    }


    @ExceptionHandler
    public ResponseEntity<?> respondWithError(Exception e) {
        return new ResponseEntity<>("Exception Occurred. More Info :"
                + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
