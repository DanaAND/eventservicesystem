package com.sda.lukaapp.categories.rest.dto;

public class UpdateCategoryRequest {

    private long id;

    private String isSocialEvent;

    private String isLifeEvent;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsSocialEvent() {
        return isSocialEvent;
    }

    public void setIsSocialEvent(String isSocialEvent) {
        this.isSocialEvent = isSocialEvent;
    }

    public String getIsLifeEvent() {
        return isLifeEvent;
    }

    public void setIsLifeEvent(String isLifeEvent) {
        this.isLifeEvent = isLifeEvent;
    }
}

