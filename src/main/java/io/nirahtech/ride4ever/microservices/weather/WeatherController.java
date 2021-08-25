package io.nirahtech.ride4ever.microservices.weather;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RequestMapping("/weathers")
@RestController
public final class WeatherController implements WeatherApi {

    @Autowired
    private WeatherService service;

    @PostMapping
    @Override
    public Weather create(@RequestBody Weather entity) {
        return this.service.create(entity);
    }

    @GetMapping("/{identifier}")
    @Override
    public Weather read(@PathVariable Integer identifier) {
        return this.service.read(identifier);
    }

    @PutMapping("/{identifier}")
    @Override
    public Weather update(@PathVariable Integer identifier, @RequestBody Weather entity) {
        return this.service.update(identifier, entity);
    }

    @DeleteMapping("/{identifier}")
    @Override
    public Weather delete(@PathVariable Integer identifier) {
        return this.service.delete(identifier);
    }

    @GetMapping
    @Override
    public List<Weather> findAll() {
        return this.service.findAll();
    }

}
