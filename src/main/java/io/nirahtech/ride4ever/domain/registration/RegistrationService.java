package io.nirahtech.ride4ever.domain.registration;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import io.nirahtech.ride4ever.core.environment.Pilot;
import io.nirahtech.ride4ever.domain.pilot.PilotService;

public final class RegistrationService implements RegistrationApi {

    private static final PilotService PILOT_SERVICE = PilotService.getInstance();

    @Override
    public Pilot create(Pilot pilot) throws RuntimeException {
        pilot.setRegistrationDate(Timestamp.from(Instant.now()));
        return PILOT_SERVICE.create(pilot);
    }
    
    @Override
    public Pilot find(String email) throws RuntimeException {
        List<Pilot> pilots = PILOT_SERVICE.findAll().stream().filter((pilot -> pilot.getEmail().equalsIgnoreCase(email))).collect(Collectors.toList());
        if (pilots != null && !pilots.isEmpty()) {
            return pilots.get(0);
        }
        return null;
    }

    @Override
    public Pilot update(Pilot pilot) throws RuntimeException {
        return PILOT_SERVICE.update(pilot.getIdentifier(), pilot);
    }

    @Override
    public Pilot delete(Pilot pilot) throws RuntimeException {
        return PILOT_SERVICE.delete(pilot.getIdentifier());
    }

}
