package io.nirahtech.ride4ever.core.environment;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
    
}
