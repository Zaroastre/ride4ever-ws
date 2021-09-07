/******************************************************************
 * Copyright 2021 Ride4Ever
 *
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.club;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("motorcycleClubService")
final class MotorcycleClubService implements MotorcycleClubApi {

    @Autowired
    private MotorcycleClubRepository repository;

    private static final MotorcycleClubService SINGLETON = new MotorcycleClubService();

    private MotorcycleClubService() { }

    public static MotorcycleClubService getInstance() {
        return SINGLETON;
    }

    @Override
    public MotorcycleClub create(MotorcycleClub entity) {
        return this.repository.save(entity);
    }

    @Override
    public MotorcycleClub read(String id) {
        Optional<MotorcycleClub> entity = this.repository.findById(id);
        if (entity.isPresent()) {
            return entity.get();
        }
        return null;
    }

    @Override
    public MotorcycleClub update(String id, MotorcycleClub entity) {
        return this.repository.save(entity);
    }

    @Override
    public void delete(String id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<MotorcycleClub> findAll() {
        List<MotorcycleClub> list = new ArrayList<>();
        this.repository.findAll().forEach((item) -> {
            list.add(item);
        });
        return list;
    }

}
