package dev.anzala.eventmanagementsystem.Models;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import dev.anzala.eventmanagementsystem.Configuration.LocalDateConverter;
import dev.anzala.eventmanagementsystem.Configuration.LocalTimeConverter;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Event extends BaseModel{
    @CsvBindByName(column = "event_name")
    private String event_name;

    @CsvBindByName(column = "city_name")
    private String city_name;

    @CsvCustomBindByName(column = "date", converter = LocalDateConverter.class)
    private LocalDate date;

    @CsvCustomBindByName(column = "time", converter = LocalTimeConverter.class)
    private LocalTime time;

    @CsvBindByName(column = "latitude")
    private double latitude;

    @CsvBindByName(column = "longitude")
    private double longitude;

    private String weather;

    private double distance_km;
}
