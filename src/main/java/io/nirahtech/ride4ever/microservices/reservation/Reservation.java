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
import javax.persistence.OneToOne;

import io.nirahtech.ride4ever.microservices.biker.Biker;
import io.nirahtech.ride4ever.microservices.roadtrip.RoadTrip;

@Entity
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roadtrip_id")
    private int identifier;

    // @OneToOne(optional = false)
    // @JoinColumn(name = "biker_id")
    private int biker;

    // @OneToOne(optional = false)
    // @JoinColumn(name = "roadtrip_id")
    private int roadTrip;
    
    private Timestamp date;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public int getBiker() {
        return biker;
    }

    public void setBiker(int biker) {
        this.biker = biker;
    }

    public int getRoadTrip() {
        return roadTrip;
    }

    public void setRoadTrip(int roadTrip) {
        this.roadTrip = roadTrip;
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

}
