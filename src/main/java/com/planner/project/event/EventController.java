package com.planner.project.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
@RequestMapping(path = "/event")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String getEvents(Model model){
        List<Event> eventsList =  eventService.getEvents();
        model.addAttribute("events", eventsList);
        System.out.println(model);
        return "event";
    }

    @PostMapping()
    public void newEvent(@RequestBody Event event){
        eventService.addNewEvent(event);
    }

    @DeleteMapping(path = "{eventId}")
    public void deleteEvent(@PathVariable("eventId") Long eventId){
        eventService.deleteEvent(eventId);
    }
}
