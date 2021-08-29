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
    private int organizer;
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

    // @ManyToOne(cascade = CascadeType.MERGE)
    // @JoinColumn(name="START_ADDRESS_ID", nullable=true, updatable=true)
    private int startAddress = 0;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=false)
    private List<Address> destinations = new ArrayList<>();

    // @ManyToOne(cascade = CascadeType.MERGE)
    // @JoinColumn(name="STOP_ADDRESS_ID", nullable=true, updatable=true)
    private int stopAddress = 0;

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

    public int getOrganizer() {
        return organizer;
    }

    public void setOrganizer(int organizer) {
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
    public int getStartAddress() {
        return startAddress;
    }
    public int getStopAddress() {
        return stopAddress;
    }
    public void setDestinations(List<Address> destinations) {
        this.destinations = destinations;
    }
    public void setStartAddress(int startAddress) {
        this.startAddress = startAddress;
    }
    public void setStopAddress(int stopAddress) {
        this.stopAddress = stopAddress;
    }

    public RoadTripStatus getStatus() {
        return status;
    }
    public void setStatus(RoadTripStatus status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bikers == null) ? 0 : bikers.hashCode());
        result = prime * result + ((candidates == null) ? 0 : candidates.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((destinations == null) ? 0 : destinations.hashCode());
        result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
        result = prime * result + identifier;
        result = prime * result + kilometersAverage;
        result = prime * result + maxBikers;
        result = prime * result + organizer;
        result = prime * result + ((roadTripType == null) ? 0 : roadTripType.hashCode());
        result = prime * result + startAddress;
        result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + stopAddress;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RoadTrip other = (RoadTrip) obj;
        if (bikers == null) {
            if (other.bikers != null)
                return false;
        } else if (!bikers.equals(other.bikers))
            return false;
        if (candidates == null) {
            if (other.candidates != null)
                return false;
        } else if (!candidates.equals(other.candidates))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (destinations == null) {
            if (other.destinations != null)
                return false;
        } else if (!destinations.equals(other.destinations))
            return false;
        if (endDate == null) {
            if (other.endDate != null)
                return false;
        } else if (!endDate.equals(other.endDate))
            return false;
        if (identifier != other.identifier)
            return false;
        if (kilometersAverage != other.kilometersAverage)
            return false;
        if (maxBikers != other.maxBikers)
            return false;
        if (roadTripType != other.roadTripType)
            return false;
        if (startDate == null) {
            if (other.startDate != null)
                return false;
        } else if (!startDate.equals(other.startDate))
            return false;
        if (status != other.status)
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    
    
}
