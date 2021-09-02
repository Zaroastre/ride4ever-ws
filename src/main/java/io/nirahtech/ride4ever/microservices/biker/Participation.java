/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.biker;

import javax.persistence.Table;

@Table
public class Participation {
    int identifier;

    public Participation(final int id) {
        this.identifier = id;
    }
}
