package com.sda.lukaapp.location.domain;


import javax.persistence.*;

@Entity(name = "Location")
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_seq_gen")
    @SequenceGenerator(name = "location_seq_gen", sequenceName = "location_seq", allocationSize = 1)
    private long id;

    @Column(name = "city")
    private String city;

    @Column(name = " adress")
    private String adress;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
