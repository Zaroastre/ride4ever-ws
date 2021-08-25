package io.nirahtech.ride4ever.microservices.biker;

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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import io.nirahtech.ride4ever.microservices.address.Address;
import io.nirahtech.ride4ever.microservices.motorbike.Motorbike;
import io.nirahtech.ride4ever.microservices.roadtrip.RoadTrip;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "email", "phoneNumber" }))
public final class Biker implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "biker_id")
    private int identifier;
    private String firstName;
    private String lastName;
    private String pseudo;
    private Timestamp birthDate;
    private Timestamp driverLicenceDate;
    private Timestamp registrationDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String email;
    private String phoneNumber;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizer")
    private List<RoadTrip> roadTrips;

    @Column(length = 200)
    private String biography;

    private String picture;

    private Address address;

    private String work;

    private boolean canRepairMotorbike;
    private boolean isTrainedForFirstRescue;

    @Enumerated(EnumType.STRING)
    private Blood blood;
    private int weight;
    private boolean isOrganDonor;
    private boolean hadHaveOperations;
    private String allergies;

    private int level;
    // private int reputation;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Motorbike> motorbikes = new ArrayList<>();

    private boolean hadAllreadyRideWithPassenger = false;

    public Biker() {

    }

    public int getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Timestamp getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Timestamp birthDate) {
        this.birthDate = birthDate;
    }

    public Timestamp getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = Gender.parse(gender);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Blood getBlood() {
        return this.blood;
    }

    public void setBlood(Blood blood) {
        this.blood = blood;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean getHadHaveOperations() {
        return this.hadHaveOperations;
    }

    public void setHadHaveOperations(boolean hadHaveOperations) {
        this.hadHaveOperations = hadHaveOperations;
    }

    public String getAllergies() {
        return this.allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }
    public List<Motorbike> getMotorbikes() {
        return motorbikes;
    }
    public void setMotorbikes(List<Motorbike> motorbikes) {
        this.motorbikes = motorbikes;
    }

    public String getBiography() {
        return biography;
    }
    public Timestamp getDriverLicenceDate() {
        return driverLicenceDate;
    }
    public String getPicture() {
        return picture;
    }
    public String getWork() {
        return work;
    }
    public void setBiography(String biography) {
        this.biography = biography;
    }
    public void setDriverLicenceDate(Timestamp driverLicenceDate) {
        this.driverLicenceDate = driverLicenceDate;
    }
    public void setWork(String work) {
        this.work = work;
    }

    public String getPseudo() {
        return pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setCanRepairMotorbike(boolean canRepairMotorbike) {
        this.canRepairMotorbike = canRepairMotorbike;
    }

    public void setIsOrganDonor(boolean isOrganDonor) {
        this.isOrganDonor = isOrganDonor;
    }

    public void setIsTrainedForFirstRescue(boolean isTrainedForFirstRescue) {
        this.isTrainedForFirstRescue = isTrainedForFirstRescue;
    }
    public void setHadAllreadyRideWithPassenger(boolean hadAllreadyRideWithPassenger) {
        this.hadAllreadyRideWithPassenger = hadAllreadyRideWithPassenger;
    }
    public boolean getCanRepairMotorbike() {
        return canRepairMotorbike;
    }
    public boolean getIsOrganDonor() {
        return isOrganDonor;
    }
    public boolean getIsTrainedForFirstRescue() {
        return isTrainedForFirstRescue;
    }
    public boolean getHadAllreadyRideWithPassenger() {
        return hadAllreadyRideWithPassenger;
    }

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Biker [address=" + address + ", allergies=" + allergies + ", biography=" + biography + ", birthDate="
                + birthDate + ", blood=" + blood + ", canRepairMotorbike=" + canRepairMotorbike + ", driverLicenceDate="
                + driverLicenceDate + ", email=" + email + ", firstName=" + firstName + ", gender=" + gender
                + ", hadAllreadyRideWithPassenger=" + hadAllreadyRideWithPassenger + ", hadHaveOperations="
                + hadHaveOperations + ", identifier=" + identifier + ", isOrganDonor=" + isOrganDonor
                + ", isTrainedForFirstRescue=" + isTrainedForFirstRescue + ", lastName=" + lastName + ", level=" + level
                + ", motorbikes=" + motorbikes + ", password=" + password + ", phoneNumber=" + phoneNumber
                + ", picture=" + picture + ", pseudo=" + pseudo + ", registrationDate=" + registrationDate + ", weight="
                + weight + ", work=" + work + "]";
    }

    
}
