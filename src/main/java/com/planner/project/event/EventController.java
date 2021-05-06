package com.planner.project.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;



@Controller
@RequestMapping
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping(path = "/newEvent")
    public ModelAndView showForm(){
        return new ModelAndView("newEvent", "event", new Event());
    }

    @PostMapping(path="/newEvent")
    public String submit(@Valid @ModelAttribute("event")Event event,
                         BindingResult result, ModelMap model, @DateTimeFormat(pattern="yyyy-MM-dd")Date date) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "error";
        }
        model.addAttribute("description", event.getDescription());
        model.addAttribute("type", event.getType());
        model.addAttribute("privacy", event.getPrivacy());
        model.addAttribute("date", event.getDate());
        model.addAttribute("time", event.getTime());

        System.out.println("Event to add:" + event);
        eventService.addNewEvent(event);
        return "/event";


    }


    @GetMapping(path = "/event")
    public String getEvents(Model model){
        List<Event> eventsList =  eventService.getEvents();
        model.addAttribute("events", eventsList);
        System.out.println(model);
        return "event";
    }

    @PostMapping(path = "/event")
    public void newEvent(@RequestBody Event event){
        eventService.addNewEvent(event);
    }

    @DeleteMapping(path = "{eventId}")
    public void deleteEvent(@PathVariable("eventId") Long eventId){
        eventService.deleteEvent(eventId);
    }
}
