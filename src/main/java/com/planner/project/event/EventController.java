package com.planner.project.event;

import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.ws.rs.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


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
        List<String> privacyOptions = new ArrayList<>();
        privacyOptions.add("public");
        privacyOptions.add("confidential");
        privacyOptions.add("personal");
        ModelAndView modelAndView = new ModelAndView("newEvent", "event", new Event());
        modelAndView.addObject("privacyOptions", privacyOptions);
        return modelAndView;

    }

    @PostMapping(path="/newEvent")
    public RedirectView submit(@Valid @ModelAttribute("event")Event event,
                               BindingResult result, ModelMap model, @DateTimeFormat(pattern="yyyy-MM-dd")Date date) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return new RedirectView("error");
        }
        model.addAttribute("description", event.getDescription());
        model.addAttribute("type", event.getType());
        model.addAttribute("privacy", event.getPrivacy());
        model.addAttribute("date", event.getDate());
        model.addAttribute("time", event.getTime());

        System.out.println("Event to add:" + event);
        eventService.addNewEvent(event);
        return new RedirectView("/events");
    }


    @GetMapping(path = "/events")
    public String getEvents(Model model){
        List<Event> eventsList =  eventService.getEvents();
        model.addAttribute("events", eventsList);
        System.out.println(model);
        return "events";
        }

    @PutMapping(path = "/event{eventId}")
    public String updateEvent(@PathVariable("eventId")Long eventId,
                            @RequestParam(required = false) String description,
                            @RequestParam(required = false) String type,
                            @RequestParam(required = false) String privacy,
                            @RequestParam(required = false) LocalDate date,
                            @RequestParam(required = false) LocalTime time){
        eventService.updateEvent(eventId,type,privacy,description,date,time);
        return "redirect:/events";
    }

    @PostMapping(path = "/delete/{eventId}")
    public String deleteEvent(@PathVariable("eventId") Long eventId){
        eventService.deleteEvent(eventId);
        return "redirect:/events";
    }

    @GetMapping(path = "/events/{eventId}")
    public ModelAndView updateEvent(@PathVariable("eventId") Long eventId){
        ModelAndView modelAndView = new ModelAndView();
        Optional<Event> eventCheck = eventService.getEvent(eventId);
        if (eventCheck.isPresent()){
            Event event = eventCheck.get();
            modelAndView.setViewName("event");
            modelAndView.addObject("event", event);
        }
        else{
            throw new IllegalArgumentException("Event with id" + eventId + "does not exist");
        }
        List<String> privacyOptions = new ArrayList<>();
        privacyOptions.add("public");
        privacyOptions.add("confidential");
        privacyOptions.add("personal");
        modelAndView.addObject("privacyOptions", privacyOptions);
        return modelAndView;
    }
}
