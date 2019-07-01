package com.sda.lukaapp.categories.rest.dto;

public class UpdateCategoryRequest {

    private long id;

    private boolean isSocialEvent;

    private boolean isLifeEvent;

    private String eventName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isSocialEvent() {
        return isSocialEvent;
    }

    public void setSocialEvent(boolean socialEvent) {
        isSocialEvent = socialEvent;
    }

    public boolean isLifeEvent() {
        return isLifeEvent;
    }

    public void setLifeEvent(boolean lifeEvent) {
        isLifeEvent = lifeEvent;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}

