package com.sda.lukaapp.location.rest.dto;

public class CreateLocationRequest {

    private String city;
    private String adress;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
