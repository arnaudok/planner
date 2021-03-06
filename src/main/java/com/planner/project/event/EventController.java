package com.planner.project.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController //  @Controller for WEB APP
@RequestMapping
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }





//    REST API MAPPING

    @GetMapping(path = "/events")
    public List<Event> getEvents(@RequestParam(required = false) String filter,
                                 @RequestParam(required = false) String month,
                                 @RequestParam(required = false) String date,
                                 @RequestParam(required = false) String type,
                                 @RequestParam(required = false) String startDate,
                                 @RequestParam(required = false) String endDate,
                                 Model model){
        System.out.println(filter);
        if (filter != null){
            switch (filter){
                case "month":
                    return eventService.getAllByMonth(month);
                case "day":
                    return eventService.getAllByDay(date);
                case "type":
                    return eventService.getAllByType(type);
                case "period":
                    System.out.println(startDate + " " +  endDate);
                    return eventService.getAllByPeriod(startDate, endDate);
            }
        }
        return eventService.getEvents();
    }
    @PostMapping(path = "/events")
    public void addNewEvent(@RequestBody Event event){
        eventService.addNewEvent(event);
    }

    @DeleteMapping(path = "/events/{eventId}")
    public void deleteEvent(@PathVariable("eventId") Long eventId, HttpServletResponse response){
        eventService.deleteEvent(eventId);
        response.setStatus(200);
    }

    @PutMapping(path = "/events/{eventId}")
    public void updateEvent(@PathVariable("eventId")Long eventId,
                            @RequestParam(required = false) String description,
                            @RequestParam(required = false) String type,
                            @RequestParam(required = false) String privacy,
                            @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd")LocalDate date,
                            @RequestParam(required = false) LocalTime time){
        System.out.println("Event to update" + eventId);
        eventService.updateEvent(eventId,type,privacy,description,date,time);
        System.out.println("Event updated!");
    }


//    WEB APP MAPPING


//    @GetMapping(path = "/newEvent")
//    public String addEvent(Model model){
//        List<String> privacyOptions = new ArrayList<>();
//        privacyOptions.add("public");
//        privacyOptions.add("confidential");
//        privacyOptions.add("personal");
//        model.addAttribute("privacyOptions", privacyOptions);
//        model.addAttribute("event", new Event());
//        return "newEvent";
//
//    }
//
//    @PostMapping(path="/newEvent")
//    public RedirectView submit(@ModelAttribute("event")Event event,
//                               BindingResult result, Model model, @DateTimeFormat(pattern="yyyy-MM-dd")LocalDate date) {
//        if (result.hasErrors()) {
//            System.out.println(result.getAllErrors());
//            return new RedirectView("error");
//        }
//        model.addAttribute("description", event.getDescription());
//        model.addAttribute("type", event.getType());
//        model.addAttribute("privacy", event.getPrivacy());
//        model.addAttribute("date", event.getDate());
//        model.addAttribute("time", event.getTime());
//
//        System.out.println("Event to add:" + event);
//        eventService.addNewEvent(event);
//        return new RedirectView("/events");
//    }
//
//
//    @GetMapping(path = "/events")
//    public String getEvents(@RequestParam(required = false) String filter,
//                            @RequestParam(required = false) String month,
//                            @RequestParam(required = false) String date,
//                            @RequestParam(required = false) String type,
//                            @RequestParam(required = false) String startDate,
//                            @RequestParam(required = false) String endDate,
//                            Model model){
//        System.out.println(filter);
//        if (filter != null){
//            switch (filter){
//                case "month":
//                    model.addAttribute("events", eventService.getAllByMonth(month));
//                    model.addAttribute("month", eventService.getMonth(month));
//                    break;
//                case "day":
//                   model.addAttribute("events", eventService.getAllByDay(date));
//                    model.addAttribute("day", eventService.getDay(date));
//                    break;
//                case "type":
//                    model.addAttribute("events", eventService.getAllByType(type));
//                    model.addAttribute("type", type);
//                    break;
//                case "period":
//                    System.out.println(startDate + " " +  endDate);
//                    model.addAttribute("events", eventService.getAllByPeriod(startDate, endDate));
//                    model.addAttribute("period", "Between " + startDate + " and " + endDate);
//                    break;
//            }
//        }
//        else {
//            model.addAttribute("events", eventService.getEvents());
//        }
//        return "events";
//    }
//
//
//    @PostMapping(path = "/events/{eventId}")
//    public String updateEvent(@PathVariable("eventId")Long eventId,
//                            @RequestParam(required = false) String description,
//                            @RequestParam(required = false) String type,
//                            @RequestParam(required = false) String privacy,
//                            @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd")LocalDate date,
//                            @RequestParam(required = false) LocalTime time){
//        System.out.println("Event to update" + eventId);
//        eventService.updateEvent(eventId,type,privacy,description,date,time);
//        System.out.println("Event updated!");
//        return "redirect:/events";
//    }
//
//    @PostMapping(path = "/delete/{eventId}")
//    public String deleteEvent(@PathVariable("eventId") Long eventId, HttpServletResponse response){
//        eventService.deleteEvent(eventId);
//        return "redirect:/events";
//    }
//
//    @GetMapping(path = "/events/{eventId}")
//    public ModelAndView updateEvent(@PathVariable("eventId") Long eventId){
//        ModelAndView modelAndView = new ModelAndView();
//        Optional<Event> eventCheck = eventService.getEvent(eventId);
//        if (eventCheck.isPresent()){
//            Event event = eventCheck.get();
//            modelAndView.setViewName("event");
//            modelAndView.addObject("event", event);
//        }
//        else{
//            throw new IllegalArgumentException("Event with id" + eventId + "does not exist");
//        }
//        List<String> privacyOptions = new ArrayList<>();
//        privacyOptions.add("public");
//        privacyOptions.add("confidential");
//        privacyOptions.add("personal");
//        modelAndView.addObject("privacyOptions", privacyOptions);
//        return modelAndView;
//    }
}
