package io.nirahtech.ride4ever.domain.biker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import io.nirahtech.ride4ever.core.environment.Motorbike;
import io.nirahtech.ride4ever.core.environment.Biker;
import io.nirahtech.ride4ever.domain.motorbike.MotorbikeService;
import io.nirahtech.ride4ever.infrastructure.exceptions.DuplicateConstraintException;

@Service
public final class BikerService implements BikerApi {

    private static final Map<Integer, Biker> DATABASE = new HashMap<>();

    private static final BikerService SINGLETON = new BikerService();

    private BikerService() {

    }

    public static BikerService getInstance() {
        return SINGLETON;
    }

    @Override
    public Biker create(Biker entity) {
        Collection<Biker> registeredBikers = DATABASE.values();
        for (Biker biker : registeredBikers) {
            if (biker.getEmail().equalsIgnoreCase(entity.getEmail())) {
                throw new DuplicateConstraintException("Email " + biker.getEmail() + " is not available.");
            }
            if (biker.getPhoneNumber().equalsIgnoreCase(entity.getPhoneNumber())) {
                throw new DuplicateConstraintException("Phone number " + biker.getPhoneNumber() + " is not available.");
            }
        }
        final MotorbikeService motorbikeService = MotorbikeService.getInstance();
        for (Motorbike motorbike :entity.getMotorbikes()) {
            motorbikeService.create(motorbike);
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
    public Biker read(Integer identifier) {
        if (DATABASE.containsKey(identifier)) {
            return DATABASE.get(identifier);
        }
        return null;
    }

    @Override
    public Biker update(Integer identifier, Biker entity) {
        if (DATABASE.containsKey(identifier)) {
            return DATABASE.replace(identifier, entity);
        }
        return null;

    }

    @Override
    public Biker delete(Integer identifier) {
        if (DATABASE.containsKey(identifier)) {
            return DATABASE.remove(identifier);
        }
        return null;
    }

    @Override
    public List<Biker> findAll() {
        return new ArrayList<>(DATABASE.values());
    }

    @Override
    public Biker findByEmail(final String email) {
        Biker biker = null;
        Optional<Biker> result = DATABASE.values().stream().filter(entity -> entity.getEmail().equalsIgnoreCase(email)).findFirst();
        if (result.isPresent()) {
            biker = result.get();
        }
        return biker;
    }

}
