package io.nirahtech.ride4ever.domain.pilot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import io.nirahtech.ride4ever.core.environment.Pilot;
import io.nirahtech.ride4ever.exceptions.DuplicateConstraintException;

public final class PilotService implements PilotApi {

    private static final Map<Integer, Pilot> DATABASE = new HashMap<>();

    private static final PilotService SINGLETON = new PilotService();

    private PilotService() {

    }

    public static PilotService getInstance() {
        return SINGLETON;
    }

    @Override
    public Pilot create(Pilot entity) {
        System.out.println(entity);
        Collection<Pilot> registeredPilots = DATABASE.values();
        for (Pilot pilot : registeredPilots) {
            if (pilot.getEmail().equalsIgnoreCase(entity.getEmail())) {
                throw new DuplicateConstraintException("Email " + pilot.getEmail() + " is not available.");
            }
            if (pilot.getPhoneNumber().equalsIgnoreCase(entity.getPhoneNumber())) {
                throw new DuplicateConstraintException("Phone number " + pilot.getPhoneNumber() + " is not available.");
            }
        }
        Set<Integer> identifiers = DATABASE.keySet();
        int maxIdentifier = 0;
        for (Integer identifier : identifiers) {
            if (identifier > maxIdentifier) {
                maxIdentifier = identifier;
            }
        }
        maxIdentifier += 1;
        entity.setIdentifier(maxIdentifier);
        DATABASE.put(maxIdentifier, entity);
        return entity;
    }

    @Override
    public Pilot read(Integer identifier) {
        if (DATABASE.containsKey(identifier)) {
            return DATABASE.get(identifier);
        }
        return null;
    }

    @Override
    public Pilot update(Integer identifier, Pilot entity) {
        if (DATABASE.containsKey(identifier)) {
            return DATABASE.replace(identifier, entity);
        }
        return null;

    }

    @Override
    public Pilot delete(Integer identifier) {
        if (DATABASE.containsKey(identifier)) {
            return DATABASE.remove(identifier);
        }
        return null;
    }

    @Override
    public List<Pilot> findAll() {
        return new ArrayList<>(DATABASE.values());
    }

    @Override
    public Pilot findByEmail(final String email) {
        Pilot pilot = null;
        System.out.println(email);
        System.out.println(DATABASE.size());
        System.out.println(DATABASE);
        Optional<Pilot> result = DATABASE.values().stream().filter(entity -> entity.getEmail().equalsIgnoreCase(email)).findFirst();
        System.out.println(result.isPresent());
        if (result.isPresent()) {
            pilot = result.get();
        }
        return pilot;
    }

}
