package com.sda.lukaapp.events.rest.dto;

import com.sda.lukaapp.events.domain.Event;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public Event toEntity(CreateEventRequest dto) {
        Event event = new Event();
        event.setName(dto.getName());
        event.setDate(dto.getDate());
        event.setCategory(dto.getCategory());
        event.setLocation(dto.getLocation());
        event.setService(dto.getServices());
        return event;
    }

    public EventResponse toDto(Event event) {
        EventResponse eventResponse = new EventResponse();
        eventResponse.setName(event.getName());
        eventResponse.setDate(event.getDate());
        eventResponse.setNoParticipants(event.getNoParticipants());
        return eventResponse;
    }
}

