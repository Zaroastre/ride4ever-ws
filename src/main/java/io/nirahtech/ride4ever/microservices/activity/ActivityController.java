package io.nirahtech.ride4ever.microservices.activity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.nirahtech.ride4ever.infrastructure.exceptions.NotImplementedException;

@CrossOrigin("*")
@RequestMapping("/activities")
@RestController
public final class ActivityController implements ActivityApi {

    @Autowired
    private ActivityService service;

    @Override
    public Activity create(@RequestBody Activity entity) {
        throw new NotImplementedException();
    }

    @Override
    public Activity read(@PathVariable Integer identifier) {
        throw new NotImplementedException();
    }

    @Override
    public Activity update(@PathVariable Integer identifier, @RequestBody Activity entity) {
        throw new NotImplementedException();
    }

    @Override
    public Activity delete(@PathVariable Integer identifier) {
        throw new NotImplementedException();
    }

    @GetMapping
    @Override
    public List<Activity> findAll() {
        return this.service.findAll();
    }

    @Override
    public List<Activity> findByEvent(EventType event) {
        // TODO Auto-generated method stub
        return null;
    }

    @GetMapping("/events-types")
    public EventType[] getEventsTypes() {
        return EventType.values();
    }
}
