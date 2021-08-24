package io.nirahtech.ride4ever.domain.registration;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import io.nirahtech.ride4ever.core.environment.Biker;
import io.nirahtech.ride4ever.domain.biker.BikerService;

public final class RegistrationService implements RegistrationApi {

    private static final BikerService PILOT_SERVICE = BikerService.getInstance();

    @Override
    public Biker create(Biker biker) throws RuntimeException {
        biker.setRegistrationDate(Timestamp.from(Instant.now()));
        return PILOT_SERVICE.create(biker);
    }
    
    @Override
    public Biker find(String email) throws RuntimeException {
        List<Biker> bikers = PILOT_SERVICE.findAll().stream().filter((biker -> biker.getEmail().equalsIgnoreCase(email))).collect(Collectors.toList());
        if (bikers != null && !bikers.isEmpty()) {
            return bikers.get(0);
        }
        return null;
    }

    @Override
    public Biker update(Biker biker) throws RuntimeException {
        return PILOT_SERVICE.update(biker.getIdentifier(), biker);
    }

    @Override
    public Biker delete(Biker biker) throws RuntimeException {
        return PILOT_SERVICE.delete(biker.getIdentifier());
    }

}
