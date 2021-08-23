package io.nirahtech.ride4ever.domain.motorbike;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import io.nirahtech.ride4ever.core.environment.Motorbike;
import io.nirahtech.ride4ever.infrastructure.exceptions.DuplicateConstraintException;

public final class MotorbikeService implements MotorbikeApi {
    private static final Map<Integer, Motorbike> DATABASE = new HashMap<>();

    private static final MotorbikeService SINGLETON = new MotorbikeService();

    private MotorbikeService() {

    }

    public static MotorbikeService getInstance() {
        return SINGLETON;
    }

    @Override
    public Motorbike create(Motorbike entity) {
        Collection<Motorbike> registeredMotorbikes = DATABASE.values();
        for (Motorbike motorbike : registeredMotorbikes) {
            if (motorbike.getLicensePlate().equalsIgnoreCase(entity.getLicensePlate())) {
                throw new DuplicateConstraintException("License plate " + motorbike.getLicensePlate() + " is not available.");
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
    public Motorbike read(Integer identifier) {
        if (DATABASE.containsKey(identifier)) {
            return DATABASE.get(identifier);
        }
        return null;
    }

    @Override
    public Motorbike update(Integer identifier, Motorbike entity) {
        if (DATABASE.containsKey(identifier)) {
            return DATABASE.replace(identifier, entity);
        }
        return null;

    }

    @Override
    public Motorbike delete(Integer identifier) {
        if (DATABASE.containsKey(identifier)) {
            return DATABASE.remove(identifier);
        }
        return null;
    }

    @Override
    public List<Motorbike> findAll() {
        return new ArrayList<>(DATABASE.values());
    }

    @Override
    public Motorbike findByLicensePlate(final String licensePlate) {
        Motorbike motorbike = null;
        Optional<Motorbike> result = DATABASE.values().stream().filter(entity -> entity.getLicensePlate().equalsIgnoreCase(licensePlate)).findFirst();
        if (result.isPresent()) {
            motorbike = result.get();
        }
        return motorbike;
    }

}
