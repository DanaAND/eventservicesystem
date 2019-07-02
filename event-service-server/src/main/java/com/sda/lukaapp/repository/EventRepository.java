package com.sda.lukaapp.repository;

import com.sda.lukaapp.events.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {


}

