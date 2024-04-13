package dev.anzala.eventmanagementsystem.Services;

import com.opencsv.bean.CsvToBeanBuilder;
import dev.anzala.eventmanagementsystem.Dtos.ResponseDto;
import dev.anzala.eventmanagementsystem.Models.Event;
import dev.anzala.eventmanagementsystem.Repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
@Service
public class EventReaderService {

@Autowired
    private  EventRepository eventRepository;

//
//    @Autowired
//    public EventReaderService(EventRepository eventRepository) {
//        this.eventRepository = eventRepository;
//    }
    private ResponseDto responseDto;

    public void readCsvAndSaveInDb(String csvFilePath) {
        try (Reader reader = Files.newBufferedReader(Paths.get("/home/anzala/Downloads/Backend_assignment_gg_dataset - dataset.csv"))) {

            com.opencsv.bean.CsvToBean<Event> csvToBean = new CsvToBeanBuilder<Event>(reader)
                    .withType(Event.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<Event> events = csvToBean.parse();

            eventRepository.saveAll(events);

        } catch (Exception ex) {
            // handle exception
        }
    }
}
