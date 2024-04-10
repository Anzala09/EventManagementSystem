package dev.anzala.eventmanagementsystem.Controller;

import dev.anzala.eventmanagementsystem.Dtos.EventFinderRequestDto;
import dev.anzala.eventmanagementsystem.Dtos.ResponseDto;
import dev.anzala.eventmanagementsystem.Models.Event;
import dev.anzala.eventmanagementsystem.Services.EventFinderService;
import dev.anzala.eventmanagementsystem.Services.EventReaderService;
import dev.anzala.eventmanagementsystem.Services.WeatherService;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
public class EventController {
   private EventReaderService eventReaderService;
   private EventFinderService eventFinderService;

   private ResponseDto responseDto;
   private WeatherService weatherService;

    public EventController(EventReaderService eventReaderService, EventFinderService eventFinderService, WeatherService weatherService) {
        this.eventReaderService = eventReaderService;
        this.eventFinderService = eventFinderService;
        this.weatherService = weatherService;
    }

    @GetMapping("/find") // GET /events/find
    public Page<ResponseDto> eventFinder(@RequestParam Double latitude, @RequestParam Double longitude, @RequestParam LocalDate date, @RequestParam int page, @RequestParam int size) {

        Pageable  pageable = PageRequest.of(page, size);

        Page<Event> eventPage = eventFinderService.findEvent(latitude,longitude,date, pageable);

        List<ResponseDto> dtos = eventPage.stream()
                .map(event -> {
                    ResponseDto dto = new ResponseDto();
                    dto.setEvent_name(event.getEvent_name());
                    dto.setCity_name(event.getCity_name());
                    dto.setDate(event.getDate());
                    dto.setWeather(event.getWeather());
                    dto.setDistance_km(event.getDistance_km());
                    return dto;
                })
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, dtos.size());
    }

//        List<Event> event = eventFinderService.findEvent(latitude,longitude,date);
//        if(event.size() == 0){
//            System.out.println("Event is not found");
//        }else {
//            System.out.println("Event is found successfully");
//        }
//
//        List<ResponseDto> list = new ArrayList<>();
//        for(Event e : event){
//            ResponseDto responseDto = new ResponseDto();
//            String jsonStr = e.getWeather();
//            //Double distancedouble = e.getDistance_km();
//            JSONObject jsonObj = new JSONObject(jsonStr);
//
//            //JSONObject jsonObj1 = new JSONObject(distancedouble);
//            //System.out.println("weather is " + jsonObj.get("weather"));
//
//            responseDto.setEvent_name(e.getEvent_name());
//            responseDto.setCity_name(e.getCity_name());
//            responseDto.setDate(e.getDate());
//            responseDto.setWeather((String)jsonObj.get("weather"));
//
//            //System.out.println("weathers is + " + e.getWeather());
//
//            responseDto.setDistance_km(e.getDistance_km());
//            list.add(responseDto);
//        }
//
//        return list;


    @PostMapping("/create")
    public void createEvent() {
        // create event
        System.out.println("Data is processing in  DB");
        eventReaderService.readCsvAndSaveInDb("https://drive.google.com/file/d/1sZXyOT_V1NcZj3dDQIKY9Ea_W7XdGum_/view?usp=drive_link");
    }
}
