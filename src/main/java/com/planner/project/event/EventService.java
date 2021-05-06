package com.planner.project.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents(){
        return eventRepository.findAll();
    }

    public void addNewEvent(Event event) {
        eventRepository.save(event);
        System.out.println(event);
    }

    public void deleteEvent(Long eventId) {
        boolean exists = eventRepository.existsById(eventId);
        if (!exists){
            throw new IllegalArgumentException("event with id: " + eventId + " does not exist!");
        }
        eventRepository.deleteById(eventId);
    }
}
