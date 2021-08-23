package io.nirahtech.ride4ever.domain.roadtrip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.nirahtech.ride4ever.core.environment.RoadTrip;

public final class RoadTripService implements RoadTripApi {
    private static final Map<Integer, RoadTrip> DATABASE = new HashMap<>();

    private static final RoadTripService SINGLETON = new RoadTripService();

    private RoadTripService() {

    }

    public static RoadTripService getInstance() {
        return SINGLETON;
    }

    @Override
    public RoadTrip create(RoadTrip entity) {
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
    public RoadTrip read(Integer identifier) {
        if (DATABASE.containsKey(identifier)) {
            return DATABASE.get(identifier);
        }
        return null;
    }

    @Override
    public RoadTrip update(Integer identifier, RoadTrip entity) {
        if (DATABASE.containsKey(identifier)) {
            return DATABASE.replace(identifier, entity);
        }
        return null;

    }

    @Override
    public RoadTrip delete(Integer identifier) {
        if (DATABASE.containsKey(identifier)) {
            return DATABASE.remove(identifier);
        }
        return null;
    }

    @Override
    public List<RoadTrip> findAll() {
        return new ArrayList<>(DATABASE.values());
    }

}
