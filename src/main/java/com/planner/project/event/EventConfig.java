package com.planner.project.event;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class EventConfig {
//
//    @Bean
//    CommandLineRunner commandLineRunner(EventRepository repository){
//        return args -> {
//            Event event1 = new Event( "meeting", "public", "meeting with Josh", LocalDate.of(2021, MAY, 12), LocalTime.of(14,23));
//            Event event2 = new Event("meeting", "private", "meeting with Ivan", LocalDate.of(2021, MAY, 13), LocalTime.of(15,23));
//            repository.saveAll(List.of(event1,event2));
//        };
//    }
}
