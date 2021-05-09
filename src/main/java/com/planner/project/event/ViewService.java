package com.planner.project.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ViewService {

    private final EventRepository eventRepository;

    @Autowired
    public ViewService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
}