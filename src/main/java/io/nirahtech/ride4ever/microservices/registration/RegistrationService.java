package io.nirahtech.ride4ever.microservices.registration;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.nirahtech.ride4ever.core.environment.Biker;
import io.nirahtech.ride4ever.microservices.biker.BikerService;

@Component("registrationService")
public final class RegistrationService implements RegistrationApi {

    @Autowired
    private BikerService service;

    @Override
    public Biker create(Biker biker) throws RuntimeException {
        biker.setRegistrationDate(Timestamp.from(Instant.now()));
        return service.create(biker);
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
