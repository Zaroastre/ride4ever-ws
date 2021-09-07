/******************************************************************
 * Copyright 2021 Ride4Ever
 *
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.weather;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("weatherService")
public final class WeatherService implements WeatherApi {

    @Autowired
    private WeatherRepository repository;


    @Override
    public Weather create(Weather entity) {
        return this.repository.save(entity);
    }

    @Override
    public Weather read(Integer identifier) {
        Optional<Weather> entity = this.repository.findById(identifier);
        if (entity.isPresent()) {
            return entity.get();
        }
        return null;
    }

    @Override
    public Weather update(Integer identifier, Weather entity) {
        return this.repository.save(entity);
    }

    @Override
    public void delete(Integer identifier) {
        this.repository.deleteById(identifier);
    }

    @Override
    public List<Weather> findAll() {
        List<Weather> list = new ArrayList<>();
        this.repository.findAll().forEach((item) -> {
            list.add(item);
        });
        return list;
    }

}
