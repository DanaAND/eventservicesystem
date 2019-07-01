package com.sda.lukaapp.events.rest.dto;

import java.util.Date;

public class EventResponse {

    private long id;

    private String name;
    private Date date;
    private int noParticipants;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNoParticipants() {
        return noParticipants;
    }

    public void setNoParticipants(int noParticipants) {
        this.noParticipants = noParticipants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


