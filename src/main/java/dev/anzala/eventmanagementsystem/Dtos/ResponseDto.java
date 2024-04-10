package dev.anzala.eventmanagementsystem.Dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
public class ResponseDto {
    private String event_name;
    private String city_name;
    private LocalDate date;
    private String weather;
    private double distance_km;
}
