package com.tekarchflights.tafflightsms.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flights {

    private Long id;
    private String flight_number;
    private String airport_code;
    private String departure;
    private String arrival;
    private LocalTime departure_time;
    private LocalTime arrival_time;
    private double price;
    private int available_seats;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
