package io.nirahtech.ride4ever;

import java.sql.Timestamp;
import java.time.Instant;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.nirahtech.ride4ever.engine.encryption.PasswordEncrypt;
import io.nirahtech.ride4ever.microservices.address.Address;
import io.nirahtech.ride4ever.microservices.address.AddressService;
import io.nirahtech.ride4ever.microservices.address.Country;
import io.nirahtech.ride4ever.microservices.biker.Biker;
import io.nirahtech.ride4ever.microservices.biker.BikerService;
import io.nirahtech.ride4ever.microservices.biker.Gender;
import io.nirahtech.ride4ever.microservices.motorbike.Brand;
import io.nirahtech.ride4ever.microservices.motorbike.Motorbike;
import io.nirahtech.ride4ever.microservices.motorbike.MotorbikeService;
import io.nirahtech.ride4ever.microservices.motorbike.MotorbikeType;
import io.nirahtech.ride4ever.microservices.roadtrip.RoadTrip;
import io.nirahtech.ride4ever.microservices.roadtrip.RoadTripService;
import io.nirahtech.ride4ever.microservices.roadtrip.RoadTripStatus;
import io.nirahtech.ride4ever.microservices.roadtrip.RoadTripType;

public class RegistrationServiceTest {
    
    @Autowired
    BikerService bikerService;
    
    @Autowired
    MotorbikeService motorbikeService;
    
    @Autowired
    RoadTripService roadTripService;
    
    @Autowired
    AddressService addressService;
    
    @Test
    public void testReservation() {
        Address address = new Address();
        address.setNumber(40);
        address.setStreet("Route de Pelleport");
        address.setState("OCCITANIE");
        address.setCity("Le Grès");
        address.setZipCode(31480);
        address.setCountry(Country.FRANCE);
        address = addressService.create(address);

        Biker biker = new Biker();
        biker.setFirstName("Nicolas");
        biker.setLastName("METIVIER");
        biker.setBirthDate(Timestamp.from(Instant.now()));
        biker.setPseudo("Jxalo");
        biker.setEmail("nicolas.a.metivier@gmail.com");
        biker.setGender(Gender.MALE.name());
        biker.setPhoneNumber("06.23.33.57.03");
        biker.setPassword(PasswordEncrypt.encrypt("Ride4Ever"));
        biker.setAddress(address);
        biker = bikerService.create(biker);

        Motorbike motorbike = new Motorbike();
        motorbike.setBrand(Brand.HONDA);
        motorbike.setModel("CBF 600 N ABS");
        motorbike.setYear(2004);
        motorbike.setType(MotorbikeType.ROADSTER);
        motorbike.setEngineDisplacement(600);
        motorbike.setMileage(25000);
        motorbike.setFuelTankSize(12);
        motorbike.setBiker(biker);
        motorbike = motorbikeService.create(motorbike);

        RoadTrip roadTrip = new RoadTrip();
        roadTrip.setTitle("title");
        roadTrip.setDescription("Description");
        roadTrip.setStartDate(Timestamp.from(Instant.now()));
        roadTrip.setEndDate(Timestamp.from(Instant.now()));
        roadTrip.setStartAddress(address);
        roadTrip.setStopAddress(address);
        roadTrip.setKilometersAverage(160);
        roadTrip.setStatus(RoadTripStatus.SOON);
        roadTrip.setRoadTripType(RoadTripType.RELAX);
        roadTrip.setMaxBikers(10);
        roadTrip.setOrganizer(biker);
        roadTrip = roadTripService.create(roadTrip);
    }
}
