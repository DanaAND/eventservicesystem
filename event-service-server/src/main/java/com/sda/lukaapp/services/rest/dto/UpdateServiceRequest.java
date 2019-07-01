package com.sda.lukaapp.services.rest.dto;

public class UpdateServiceRequest {

    private String type;

    private String details;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
