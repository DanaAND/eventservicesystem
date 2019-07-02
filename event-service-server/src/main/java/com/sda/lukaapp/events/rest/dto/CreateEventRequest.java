package com.sda.lukaapp.events.rest.dto;

import com.sda.lukaapp.categories.domain.Category;
import com.sda.lukaapp.location.domain.Location;
import com.sda.lukaapp.servicess.domain.Services;
import com.sda.lukaapp.users.domain.User;

import java.util.Date;

public class CreateEventRequest {

    private String name;

    private Date date;

    private int noParticipants;

    private User user;

    private Location location;

    private Services services;

    private Category category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}


