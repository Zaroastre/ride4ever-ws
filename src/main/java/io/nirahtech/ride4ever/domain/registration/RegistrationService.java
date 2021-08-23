package io.nirahtech.ride4ever.domain.registration;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import io.nirahtech.ride4ever.core.environment.Biker;
import io.nirahtech.ride4ever.domain.pilot.PilotService;

public final class RegistrationService implements RegistrationApi {

    private static final PilotService PILOT_SERVICE = PilotService.getInstance();

    @Override
    public Biker create(Biker pilot) throws RuntimeException {
        pilot.setRegistrationDate(Timestamp.from(Instant.now()));
        return PILOT_SERVICE.create(pilot);
    }
    
    @Override
    public Biker find(String email) throws RuntimeException {
        List<Biker> pilots = PILOT_SERVICE.findAll().stream().filter((pilot -> pilot.getEmail().equalsIgnoreCase(email))).collect(Collectors.toList());
        if (pilots != null && !pilots.isEmpty()) {
            return pilots.get(0);
        }
        return null;
    }

    @Override
    public Biker update(Biker pilot) throws RuntimeException {
        return PILOT_SERVICE.update(pilot.getIdentifier(), pilot);
    }

    @Override
    public Biker delete(Biker pilot) throws RuntimeException {
        return PILOT_SERVICE.delete(pilot.getIdentifier());
    }

}
