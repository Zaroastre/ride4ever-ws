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
    public Weather delete(Integer identifier) {
        Weather entity = this.read(identifier);
        if (entity != null) {
            this.repository.deleteById(entity.getIdentifier());
        }
        return entity;
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
