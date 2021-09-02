/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.reservation;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.nirahtech.ride4ever.microservices.biker.Biker;
import io.nirahtech.ride4ever.microservices.motorbike.Motorbike;
import io.nirahtech.ride4ever.microservices.roadtrip.RoadTrip;

@Entity
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private int identifier;

    @ManyToOne
    @JoinColumn(name = "biker_id")
    private Biker biker;

    @ManyToOne
    @JoinColumn(name = "roadtrip_id")
    private RoadTrip roadTrip;
    
    @ManyToOne
    @JoinColumn(name = "motorbike_id")
    private Motorbike motorbike;

    private Timestamp date;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public Biker getBiker() {
        return biker;
    }

    public void setBiker(Biker biker) {
        this.biker = biker;
    }

    public RoadTrip getRoadTrip() {
        return roadTrip;
    }

    public void setRoadTrip(RoadTrip roadTrip) {
        this.roadTrip = roadTrip;
    }

    public Motorbike getMotorbike() {
        return motorbike;
    }
    public void setMotorbike(Motorbike motorbike) {
        this.motorbike = motorbike;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation [biker=" + biker + ", date=" + date + ", identifier=" + identifier + ", roadTrip="
                + roadTrip + ", status=" + status + "]";
    }

}
