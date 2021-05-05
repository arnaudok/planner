package com.planner.project.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;



@Controller
@RequestMapping
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(path = "/event")
    public String getEvents(Model model){
        List<Event> eventsList =  eventService.getEvents();
        model.addAttribute("events", eventsList);
        System.out.println(model);
        return "event";
    }
}
