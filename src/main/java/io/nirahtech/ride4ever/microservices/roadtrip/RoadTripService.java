/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.roadtrip;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("roadTripService")
public final class RoadTripService implements RoadTripApi {

    @Autowired
    private RoadTripRepository repository;

    private static final RoadTripService SINGLETON = new RoadTripService();

    private RoadTripService() { }

    public static RoadTripService getInstance() {
        return SINGLETON;
    }

    @Override
    public RoadTrip create(RoadTrip entity) {
        return this.repository.save(entity);
    }

    @Override
    public RoadTrip read(Integer identifier) {
        Optional<RoadTrip> entity = this.repository.findById(identifier);
        if (entity.isPresent()) {
            return entity.get();
        }
        return null;
    }

    @Override
    public RoadTrip update(Integer identifier, RoadTrip entity) {
        return this.repository.save(entity);
    }

    @Override
    public RoadTrip delete(Integer identifier) {
        RoadTrip entity = this.read(identifier);
        if (entity != null) {
            this.repository.deleteById(entity.getIdentifier());
        }
        return entity;
    }

    @Override
    public List<RoadTrip> findAll() {
        List<RoadTrip> list = new ArrayList<>();
        this.repository.findAll().forEach((item) -> {
            list.add(item);
        });
        return list;
    }

}
