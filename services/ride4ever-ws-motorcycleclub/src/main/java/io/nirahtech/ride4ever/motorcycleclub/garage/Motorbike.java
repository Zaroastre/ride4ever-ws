/******************************************************************
 * Copyright 2021 Ride4Ever
 *
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.motorcycleclub.garage;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import io.nirahtech.ride4ever.motorcycleclub.human.Biker;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "licensePlate" }))
public class Motorbike implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int identifier;
    private String licensePlate;
    private int mileage;
    @Enumerated(EnumType.STRING)
    private Brand brand;
    private String model;
    private String color;
    private int engineDisplacement;
    private int year;
    private int fuelTankSize;
    private String picture;
    @Enumerated(EnumType.STRING)
    private MotorbikeType type;
    private boolean isRestrained = false;

    @ManyToOne
    @JoinColumn(name="biker_id")
    private Biker biker;

    public Motorbike() {

    }

    public int getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getLicensePlate() {
        return this.licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getMileage() {
        return this.mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public Brand getBrand() {
        return this.brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getEngineDisplacement() {
        return this.engineDisplacement;
    }

    public void setEngineDisplacement(int engineDisplacement) {
        this.engineDisplacement = engineDisplacement;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getFuelTankSize() {
        return this.fuelTankSize;
    }

    public void setFuelTankSize(int fuelTankSize) {
        this.fuelTankSize = fuelTankSize;
    }

    public MotorbikeType getType() {
        return this.type;
    }

    public void setType(MotorbikeType type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }
    public String getPicture() {
        return picture;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Biker getBiker() {
        return biker;
    }
    public void setBiker(Biker biker) {
        this.biker = biker;
    }

    public void setIsRestrained(boolean isRestrained) {
        this.isRestrained = isRestrained;
    }
    public boolean getIsRestrained() {
        return isRestrained;
    }

    @Override
    public String toString() {
        return "Motorbike [brand=" + brand + ", color=" + color + ", engineDisplacement=" + engineDisplacement
                + ", fuelTankSize=" + fuelTankSize + ", identifier=" + identifier + ", isRestrained=" + isRestrained
                + ", licensePlate=" + licensePlate + ", mileage=" + mileage + ", model=" + model + ", picture="
                + picture + ", type=" + type + ", year=" + year + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((brand == null) ? 0 : brand.hashCode());
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + engineDisplacement;
        result = prime * result + fuelTankSize;
        result = prime * result + identifier;
        result = prime * result + (isRestrained ? 1231 : 1237);
        result = prime * result + ((licensePlate == null) ? 0 : licensePlate.hashCode());
        result = prime * result + mileage;
        result = prime * result + ((model == null) ? 0 : model.hashCode());
        result = prime * result + ((picture == null) ? 0 : picture.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        result = prime * result + year;
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
        Motorbike other = (Motorbike) obj;
        if (brand != other.brand)
            return false;
        if (color == null) {
            if (other.color != null)
                return false;
        } else if (!color.equals(other.color))
            return false;
        if (engineDisplacement != other.engineDisplacement)
            return false;
        if (fuelTankSize != other.fuelTankSize)
            return false;
        if (identifier != other.identifier)
            return false;
        if (isRestrained != other.isRestrained)
            return false;
        if (licensePlate == null) {
            if (other.licensePlate != null)
                return false;
        } else if (!licensePlate.equals(other.licensePlate))
            return false;
        if (mileage != other.mileage)
            return false;
        if (model == null) {
            if (other.model != null)
                return false;
        } else if (!model.equals(other.model))
            return false;
        if (picture == null) {
            if (other.picture != null)
                return false;
        } else if (!picture.equals(other.picture))
            return false;
        if (type != other.type)
            return false;
        if (year != other.year)
            return false;
        return true;
    }



}
