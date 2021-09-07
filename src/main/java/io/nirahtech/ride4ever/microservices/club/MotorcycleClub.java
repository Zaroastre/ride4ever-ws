/******************************************************************
 * Copyright 2021 Ride4Ever
 *
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.club;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MotorcycleClub implements Serializable {

    @Id
    private String name;

    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public MotorcycleClub() {

    }

    public MotorcycleClub(final String name) {
        this.name = name;
    }
}
