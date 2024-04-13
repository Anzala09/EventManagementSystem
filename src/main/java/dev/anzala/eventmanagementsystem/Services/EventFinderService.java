package dev.anzala.eventmanagementsystem.Services;

import dev.anzala.eventmanagementsystem.Dtos.EventCreateRequestDto;
import dev.anzala.eventmanagementsystem.Dtos.EventFinderRequestDto;
import dev.anzala.eventmanagementsystem.Dtos.ResponseDto;
import dev.anzala.eventmanagementsystem.Models.Event;
import dev.anzala.eventmanagementsystem.Repository.EventRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.json.JSONObject;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@Setter

public class EventFinderService {


    private EventRepository eventRepository;
    private ResponseDto responseDto;
    private WeatherService weatherService;
    private Event event;
    private DistanceCalculation distanceCalculation;


    public EventFinderService(EventRepository eventRepository, WeatherService weatherService, DistanceCalculation distanceCalculation) {
        this.eventRepository = eventRepository;
        this.weatherService = weatherService;
        this.distanceCalculation = distanceCalculation;
    }

    public Page<Event> findEvent(Double latitude , Double longitude , LocalDate date, Pageable pageable) {
        List<Event> events = eventRepository.findAll();
        System.out.println("Total events in the database: " + events.size());
        LocalDate dateAfter14Days = date.plusDays(14);
        events = events.stream()
                .filter(event -> (event.getDate().isAfter(date) || event.getDate().isEqual(date)) && event.getDate().isBefore(dateAfter14Days))
                .collect(Collectors.toList());
        System.out.println("Events after filtering by date: " + events.size());

        events.sort(Comparator.comparing(Event::getDate));
        for(Event t : events){
            String weather = weatherService.getWeather(t.getCity_name(),t.getDate());
            String jsonStr = weather;
            JSONObject jsonObj = new JSONObject(jsonStr);
            // System.out.println("Weather data: " + weather);
            t.setWeather((String)jsonObj.get("weather"));
            // System.out.println(t.getEventName());
            Double distance = distanceCalculation.getDistance(latitude, longitude, t.getLatitude(), t.getLongitude());
            t.setDistance_km(distance);
        }
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), events.size());
        List<Event> pagedEvents = events.subList(start, end);
        return new PageImpl<>(pagedEvents, pageable, events.size());
        // return events;
    }


    public void createEvent(EventCreateRequestDto eventCreateRequestDto) {
        Event event = new Event();
        event.setEvent_name(eventCreateRequestDto.getEvent_name());
        event.setCity_name(eventCreateRequestDto.getCity_name());
        event.setDate(eventCreateRequestDto.getDate());
        event.setTime(eventCreateRequestDto.getTime());
        event.setLatitude(eventCreateRequestDto.getLatitude());
        event.setLongitude(eventCreateRequestDto.getLongitude());
        eventRepository.save(event);
    }
}

