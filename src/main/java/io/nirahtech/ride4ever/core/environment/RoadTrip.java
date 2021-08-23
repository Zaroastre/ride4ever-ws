package io.nirahtech.ride4ever.core.environment;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class RoadTrip implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int identifier;

    @Column(nullable = false)
    private String title;
    private String description = null;

    @Column(nullable = false)
    private Biker organizer;
    private int maxPilots = 0;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Biker> candidates = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Biker> pilots = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private RoadTripType roadTripType;

    @Column(nullable = false)
    private Timestamp startDate;
    private Timestamp endDate;

    @Column(nullable = false)
    private Place startPlace = null;

    // @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
    // private List<Place> places = new ArrayList<>();

    @Column(nullable = false)
    private Place stopPlace = null;

    private int kilometersAverage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Biker getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Biker organizer) {
        this.organizer = organizer;
    }

    public int getMaxPilots() {
        return maxPilots;
    }

    public void setMaxPilots(int maxPilots) {
        this.maxPilots = maxPilots;
    }

    public List<Biker> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Biker> candidates) {
        this.candidates = candidates;
    }

    public List<Biker> getPilots() {
        return pilots;
    }

    public void setPilots(List<Biker> pilots) {
        this.pilots = pilots;
    }

    public RoadTripType getRoadTripType() {
        return roadTripType;
    }

    public void setRoadTripType(RoadTripType roadTripType) {
        this.roadTripType = roadTripType;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Place getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(Place startPlace) {
        this.startPlace = startPlace;
    }

    // public List<Place> getPlaces() {
    //     return places;
    // }

    // public void setPlaces(List<Place> places) {
    //     this.places = places;
    // }

    public Place getStopPlace() {
        return stopPlace;
    }

    public void setStopPlace(Place stopPlace) {
        this.stopPlace = stopPlace;
    }

    public int getKilometersAverage() {
        return kilometersAverage;
    }

    public void setKilometersAverage(int kilometersAverage) {
        this.kilometersAverage = kilometersAverage;
    }

    public int getIdentifier() {
        return identifier;
    }
    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

}
