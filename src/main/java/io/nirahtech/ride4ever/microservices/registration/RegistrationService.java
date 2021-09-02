/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.registration;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.nirahtech.ride4ever.engine.encryption.PasswordEncrypt;
import io.nirahtech.ride4ever.microservices.activity.Activity;
import io.nirahtech.ride4ever.microservices.activity.ActivityService;
import io.nirahtech.ride4ever.microservices.activity.EventType;
import io.nirahtech.ride4ever.microservices.biker.Biker;
import io.nirahtech.ride4ever.microservices.biker.BikerService;

@Component("registrationService")
public final class RegistrationService implements RegistrationApi {

    @Autowired
    private BikerService service;

    @Autowired
    private ActivityService activityService;


    @Override
    public Biker create(Biker biker) throws RuntimeException {
        biker.setRegistrationDate(Timestamp.from(Instant.now()));
        biker.setPassword(PasswordEncrypt.encrypt(biker.getPassword()));
        Biker account = service.create(biker);
        if (account != null) {
            activityService.create(new Activity(Timestamp.from(Instant.now()), EventType.ACCOUNT_REGISTRATION, account.getPseudo(), null));
        }
        return account;
    }
    
    @Override
    public Biker find(String email) throws RuntimeException {
        List<Biker> bikers = service.findAll().stream().filter((biker -> biker.getEmail().equalsIgnoreCase(email))).collect(Collectors.toList());
        if (bikers != null && !bikers.isEmpty()) {
            return bikers.get(0);
        }
        return null;
    }

    @Override
    public Biker update(Biker biker) throws RuntimeException {
        return service.update(biker.getIdentifier(), biker);
    }

    @Override
    public Biker delete(Biker biker) throws RuntimeException {
        return service.delete(biker.getIdentifier());
    }

}
