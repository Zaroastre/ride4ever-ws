/******************************************************************
 * Copyright 2021 Ride4Ever
 *
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.motorbike;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("motorbikeService")
public final class MotorbikeService implements MotorbikeApi {

    @Autowired
    private MotorbikeRepository repository;

    private static final MotorbikeService SINGLETON = new MotorbikeService();

    private MotorbikeService() { }

    public static MotorbikeService getInstance() {
        return SINGLETON;
    }

    @Override
    public Motorbike create(Motorbike entity) {
        return this.repository.save(entity);
    }

    @Override
    public Motorbike read(Integer identifier) {
        Optional<Motorbike> entity = this.repository.findById(identifier);
        if (entity.isPresent()) {
            return entity.get();
        }
        return null;
    }

    @Override
    public Motorbike update(Integer identifier, Motorbike entity) {
        return this.repository.save(entity);
    }

    @Override
    public void delete(Integer identifier) {
        this.repository.deleteById(identifier);
    }

    @Override
    public List<Motorbike> findAll() {
        List<Motorbike> list = new ArrayList<>();
        this.repository.findAll().forEach((item) -> {
            list.add(item);
        });
        return list;
    }

    @Override
    public Motorbike findByLicensePlate(String licensePlate) {
        return this.repository.findByLicensePlate(licensePlate);
    }

    @Override
    public List<Motorbike> findByBikerPseudo(String pseudo) {
        return this.repository.findByBikerPseudo(pseudo);
    }

}
