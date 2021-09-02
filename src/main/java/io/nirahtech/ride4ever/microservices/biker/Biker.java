/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.biker;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import io.nirahtech.ride4ever.microservices.address.Address;
import io.nirahtech.ride4ever.microservices.motorbike.Motorbike;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "email", "phoneNumber", "pseudo" }))
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

    @Column(length = 200)
    private String biography;

    private String picture;

    @ManyToOne
    @JoinColumn(name="address_id")
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

    @OneToMany(mappedBy = "biker")
    private Set<Motorbike> motorbikes = new HashSet<>();

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
                + /* ", motorbikes=" + motorbikes + */ ", password=" + password + ", phoneNumber=" + phoneNumber
                + ", picture=" + picture + ", pseudo=" + pseudo + ", registrationDate=" + registrationDate + ", weight="
                + weight + ", work=" + work + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((allergies == null) ? 0 : allergies.hashCode());
        result = prime * result + ((biography == null) ? 0 : biography.hashCode());
        result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
        result = prime * result + ((blood == null) ? 0 : blood.hashCode());
        result = prime * result + (canRepairMotorbike ? 1231 : 1237);
        result = prime * result + ((driverLicenceDate == null) ? 0 : driverLicenceDate.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        result = prime * result + (hadAllreadyRideWithPassenger ? 1231 : 1237);
        result = prime * result + (hadHaveOperations ? 1231 : 1237);
        result = prime * result + identifier;
        result = prime * result + (isOrganDonor ? 1231 : 1237);
        result = prime * result + (isTrainedForFirstRescue ? 1231 : 1237);
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + level;
        result = prime * result + ((motorbikes == null) ? 0 : motorbikes.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
        result = prime * result + ((picture == null) ? 0 : picture.hashCode());
        result = prime * result + ((pseudo == null) ? 0 : pseudo.hashCode());
        result = prime * result + ((registrationDate == null) ? 0 : registrationDate.hashCode());
        result = prime * result + weight;
        result = prime * result + ((work == null) ? 0 : work.hashCode());
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
        Biker other = (Biker) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (allergies == null) {
            if (other.allergies != null)
                return false;
        } else if (!allergies.equals(other.allergies))
            return false;
        if (biography == null) {
            if (other.biography != null)
                return false;
        } else if (!biography.equals(other.biography))
            return false;
        if (birthDate == null) {
            if (other.birthDate != null)
                return false;
        } else if (!birthDate.equals(other.birthDate))
            return false;
        if (blood != other.blood)
            return false;
        if (canRepairMotorbike != other.canRepairMotorbike)
            return false;
        if (driverLicenceDate == null) {
            if (other.driverLicenceDate != null)
                return false;
        } else if (!driverLicenceDate.equals(other.driverLicenceDate))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (gender != other.gender)
            return false;
        if (hadAllreadyRideWithPassenger != other.hadAllreadyRideWithPassenger)
            return false;
        if (hadHaveOperations != other.hadHaveOperations)
            return false;
        if (identifier != other.identifier)
            return false;
        if (isOrganDonor != other.isOrganDonor)
            return false;
        if (isTrainedForFirstRescue != other.isTrainedForFirstRescue)
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (level != other.level)
            return false;
        if (motorbikes == null) {
            if (other.motorbikes != null)
                return false;
        } else if (!motorbikes.equals(other.motorbikes))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (phoneNumber == null) {
            if (other.phoneNumber != null)
                return false;
        } else if (!phoneNumber.equals(other.phoneNumber))
            return false;
        if (picture == null) {
            if (other.picture != null)
                return false;
        } else if (!picture.equals(other.picture))
            return false;
        if (pseudo == null) {
            if (other.pseudo != null)
                return false;
        } else if (!pseudo.equals(other.pseudo))
            return false;
        if (registrationDate == null) {
            if (other.registrationDate != null)
                return false;
        } else if (!registrationDate.equals(other.registrationDate))
            return false;
        if (weight != other.weight)
            return false;
        if (work == null) {
            if (other.work != null)
                return false;
        } else if (!work.equals(other.work))
            return false;
        return true;
    }

}
