package com.sda.lukaapp.events;

import com.sda.lukaapp.events.domain.Event;
import com.sda.lukaapp.events.domain.EventRepository;
import com.sda.lukaapp.events.rest.dto.CreateEventRequest;
import com.sda.lukaapp.events.rest.dto.EventMapper;
import com.sda.lukaapp.events.rest.dto.EventResponse;
import com.sda.lukaapp.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sda.lukaapp.exception.NotFoundException.ErrorCode.EVENT_NOT_FOUND;

@Service
public class EventService {

    private EventRepository eventRepository;
    private EventMapper eventMapper;

    @Autowired
    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event findById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new NotFoundException(EVENT_NOT_FOUND, "event not found"));
        return event;

//        Optional<Event> eventOptional = eventRepository.findById(id);
//        if (!eventOptional.isPresent()) {
//            throw new EventNotFoundException("Event not found");
//        }
//        return eventOptional.get();
    }

    public EventResponse save(CreateEventRequest request) {
        Event event = eventMapper.toEntity(request);
        Event newEvent = eventRepository.save(event);

        return eventMapper.toDto(newEvent);
    }


    public void deleteById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new NotFoundException(EVENT_NOT_FOUND, "event not found"));
        eventRepository.delete(event);

    }

}

