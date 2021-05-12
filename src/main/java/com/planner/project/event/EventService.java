package com.planner.project.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
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
        System.out.println(event);
        eventRepository.save(event);
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

    public List<Event> getAllByMonth(String yearMonth){
        String[] arr = yearMonth.split("-");
        String yearStr = arr[0];
        String monthStr = arr[1];
        LocalDate date = LocalDate.of(Integer.parseInt(yearStr), Integer.parseInt(monthStr), 1);
        Month month = date.getMonth();
        int lastDay = date.lengthOfMonth();
        int year = date.getYear();
        LocalDate endDate = LocalDate.of(year, month, lastDay);
        System.out.println(date + " " + endDate);
        return eventRepository.findAllByDateBetweenOrderByDateAscTimeAsc(date, endDate);
    }

    public List<Event> getAllByDay(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date,formatter);
        return eventRepository.findAllByDateOrderByDateAscTimeAsc(localDate);
    }

    public List<Event> getAllByType(String type){
        return eventRepository.findAllByTypeOrderByDateAscTimeAsc(type);
    }

    public List<Event> getAllByPeriod(String date1, String date2){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(date1, formatter);
        LocalDate endDate = LocalDate.parse(date2, formatter);
        return eventRepository.findAllByDateBetweenOrderByDateAscTimeAsc(startDate, endDate);
    }

    public String getDay(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date,formatter);
        String day = String.valueOf(localDate.getDayOfMonth());
        Month month = localDate.getMonth();
        return day + " of " + month;
    }
    public String getMonth (String yearMonth){
        String[] arr = yearMonth.split("-");
        String yearStr = arr[0];
        String monthStr = arr[1];
        LocalDate date = LocalDate.of(Integer.parseInt(yearStr), Integer.parseInt(monthStr), 1);
        Month month =  date.getMonth();
        return month + "";
    }
}
