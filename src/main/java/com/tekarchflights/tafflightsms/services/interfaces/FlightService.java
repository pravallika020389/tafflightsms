package com.tekarchflights.tafflightsms.services.interfaces;

import com.tekarchflights.tafflightsms.models.Flights;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FlightService {
    Flights addFlight(Flights flight);
    ResponseEntity<Object> getFlightById(Long flightId);

    List<Flights> getAllFlights();

    void updateFlightDetails(Long id, Flights flight);

    void deleteFlight(Long id);
}
