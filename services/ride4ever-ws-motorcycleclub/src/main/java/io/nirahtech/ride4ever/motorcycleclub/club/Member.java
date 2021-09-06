package io.nirahtech.ride4ever.motorcycleclub.club;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.nirahtech.ride4ever.motorcycleclub.human.Biker;
import jakarta.persistence.Id;

// https://www.youtube.com/watch?v=UDC1sEdJj1Y

@Entity
public class Member {

    @Id
    @GeneratedValue
    private int identifier;

    private Biker biker;
    private String role;
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp sinceDate;
    private MotorcycleClub motorcycleClub;

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
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public Timestamp getSinceDate() {
        return sinceDate;
    }
    public void setSinceDate(Timestamp sinceDate) {
        this.sinceDate = sinceDate;
    }
    public MotorcycleClub getMotorcycleClub() {
        return motorcycleClub;
    }
    public void setMotorcycleClub(MotorcycleClub motorcycleClub) {
        this.motorcycleClub = motorcycleClub;
    }

}
