package dev.anzala.eventmanagementsystem.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
@Getter
@Setter
public class EventCreateRequestDto {
    private String event_name;
    private String city_name;
    private LocalDate date;
    private LocalTime time;
    private double latitude;
    private double longitude;

}
