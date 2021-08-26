package io.nirahtech.ride4ever.microservices.roadtrip;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import io.nirahtech.ride4ever.microservices.address.Address;
import io.nirahtech.ride4ever.microservices.biker.Biker;

@Entity
public class RoadTrip implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roadtrip_id")
    private int identifier;

    @Column(nullable = false)
    private String title;
    private String description = null;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "biker_id")
    private Biker organizer;
    private int maxBikers = 0;



    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=false)
    private List<Biker> candidates = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=false)
    private List<Biker> bikers = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private RoadTripType roadTripType;

    
    @Enumerated(EnumType.STRING)
    private RoadTripStatus status;

    @Column(nullable = false)
    private Timestamp startDate;
    private Timestamp endDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="START_ADDRESS_ID", nullable=true, updatable=true)
    private Address startAddress = null;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=false)
    private List<Address> destinations = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="STOP_ADDRESS_ID", nullable=true, updatable=true)
    private Address stopAddress = null;

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

    public int getMaxBikers() {
        return maxBikers;
    }
    public void setMaxBikers(int maxBikers) {
        this.maxBikers = maxBikers;
    }
    
    public List<Biker> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Biker> candidates) {
        this.candidates = candidates;
    }

    public List<Biker> getBikers() {
        return bikers;
    }
    public void setBikers(List<Biker> bikers) {
        this.bikers = bikers;
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
    public List<Address> getDestinations() {
        return destinations;
    }
    public Address getStartAddress() {
        return startAddress;
    }
    public Address getStopAddress() {
        return stopAddress;
    }
    public void setDestinations(List<Address> destinations) {
        this.destinations = destinations;
    }
    public void setStartAddress(Address startAddress) {
        this.startAddress = startAddress;
    }
    public void setStopAddress(Address stopAddress) {
        this.stopAddress = stopAddress;
    }

    public RoadTripStatus getStatus() {
        return status;
    }
    public void setStatus(RoadTripStatus status) {
        this.status = status;
    }
    
}