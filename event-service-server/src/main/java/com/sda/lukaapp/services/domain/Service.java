package com.sda.lukaapp.services.domain;

import javax.persistence.*;

@Entity(name = "Service")
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "service_seq_gen")
    @SequenceGenerator(name = "service_seq_gen",
            sequenceName = "service_seq", allocationSize = 1)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "details")
    private String details;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

