package com.planner.project.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents(){
        return eventRepository.findAllByOrderByDateAscTimeAsc();
    }

    public Optional<Event> getEvent(Long eventId){
        return  eventRepository.findById(eventId);
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

    @Transactional
    public void updateEvent(Long eventId, String type, String privacy, String description, LocalDate date, LocalTime time){
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new IllegalStateException("Event with if " + eventId + "does not exists"));
        if (description != null && description.length() > 0 && !Objects.equals(event.getDescription(), description)){
            event.setDescription(description);
        }
        if (type != null && type.length() > 0 && !Objects.equals(event.getType(), type)){
            event.setType(type);
        }
        if (privacy != null && privacy.length() > 0 && !Objects.equals(event.getPrivacy(), privacy)){
            event.setPrivacy(privacy);
        }
        if (date != null && !Objects.equals(event.getDate(), date)){
            event.setDate(date);
        }
        if (time != null && !Objects.equals(event.getTime(), time)){
            event.setTime(time);
        }
    }
}
