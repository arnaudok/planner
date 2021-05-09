package com.planner.project.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping
public class EventController {

    private final EventService eventService;
    private final ViewService viewService;

    @Autowired
    public EventController(EventService eventService, ViewService viewService) {
        this.eventService = eventService;
        this.viewService = viewService;
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
    public RedirectView submit(@ModelAttribute("event")Event event,
                               BindingResult result, ModelMap model, @DateTimeFormat(pattern="yyyy-MM-dd")LocalDate date) {
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

        //view options
        List<String> viewOptions = new ArrayList<>();
        viewOptions.add("All");
        viewOptions.add("by day");
        viewOptions.add("by week");
        viewOptions.add("by month");
        model.addAttribute("viewOptions", viewOptions);

        return "events";
        }

        @PostMapping(path = "/events")
        public String viewEvents(@RequestParam(required = false) String viewOptions,
                                 @RequestParam(required = false) String yearMonth,
                                 Model model){
            System.out.println("View option" + viewOptions);
            System.out.println(yearMonth);
            String[] arr = yearMonth.split("-");
            String year = arr[0];
            String month = arr[1];
            LocalDate date = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 1);
            System.out.println(date);
            List<Event> events = eventService.getAllByMonth(date);
            System.out.println(events);
            model.addAttribute("events", events);
            model.addAttribute("month", month);
            return "events";
        }

    @PostMapping(path = "/events/{eventId}")
    public String updateEvent(@PathVariable("eventId")Long eventId,
                            @RequestParam(required = false) String description,
                            @RequestParam(required = false) String type,
                            @RequestParam(required = false) String privacy,
                            @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd")LocalDate date,
                            @RequestParam(required = false) LocalTime time){
        System.out.println("Event to update" + eventId);
        eventService.updateEvent(eventId,type,privacy,description,date,time);
        System.out.println("Event updated!");
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

    //View options


}
