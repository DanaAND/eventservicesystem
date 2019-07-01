package com.sda.lukaapp.events.rest;

import com.sda.lukaapp.events.EventService;
import com.sda.lukaapp.events.domain.Event;
import com.sda.lukaapp.events.rest.dto.CreateEventRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public void addEvent(CreateEventRequest createEventRequest) {
        eventService.save(createEventRequest);
    }

    @GetMapping
    public List<Event> findAll() {
        return eventService.findAll();
    }
}

