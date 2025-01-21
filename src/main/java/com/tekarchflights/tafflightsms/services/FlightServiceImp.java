package com.tekarchflights.tafflightsms.services;


import com.tekarchflights.tafflightsms.models.Flights;
import com.tekarchflights.tafflightsms.services.interfaces.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FlightServiceImp implements FlightService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${datastore.flight.service.url}")
    private String dataStore_Flight_Url;

    @Override
    public Flights addFlight(Flights flight) {
        return restTemplate.postForObject(dataStore_Flight_Url + "add", flight, Flights.class);
    }

    @Override
    public ResponseEntity<Object> getFlightById(Long flightId) {
        Flights flight = restTemplate.getForObject(dataStore_Flight_Url + flightId, Flights.class);
        if (flight != null) {

            return ResponseEntity.ok(flight);
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight with id " + flightId + " not found ");
        }

    }

    @Override
    public List<Flights> getAllFlights() {
        String url = dataStore_Flight_Url + "allflights";

        //return restTemplate.getForObject(DataStore_Url + "allfligts", List<Flights>.class);

        ResponseEntity<List<Flights>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Flights>>() {
                }
        );

        return response.getBody();
    }

    @Override
    public void updateFlightDetails(Long flightId, Flights flight) {
        try {
            restTemplate.put(dataStore_Flight_Url + flightId, flight);
        } catch (HttpClientErrorException e) {
            System.out.println("Error: " + e.getStatusCode());
        }
    }

    @Override
    public void deleteFlight(Long flightId) {
        try {
            restTemplate.delete(dataStore_Flight_Url + flightId);
        } catch (HttpClientErrorException e) {
            System.out.println("Error: " + e.getStatusCode());
        }
    }
//trial for cicd

}
