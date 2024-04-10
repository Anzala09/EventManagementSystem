package dev.anzala.eventmanagementsystem.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EventFinderRequestDto {
    private double latitude;
    private double longitude;
    private LocalDate date;

}
