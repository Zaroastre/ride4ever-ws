package io.nirahtech.ride4ever.microservices.activity;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

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

    @Override
    public List<Activity> findAll() {
        return this.service.findAll();
    }

    @GetMapping
    public List<Activity> findAll(HttpServletRequest request) {
        List<Activity> activities = null;
        if (request.getParameterMap().keySet().contains("pseudo")) {
            activities = this.findByPseudo(request.getParameter("pseudo"));
        }
        if (request.getParameterMap().keySet().contains("event")) {
            if (activities == null) {
                activities = this.findByEvent(EventType.parse(request.getParameter("event")));
            } else {
                activities = activities
                    .stream()
                    .filter((activity) -> activity.getEvent() == EventType.parse(request.getParameter("event")))
                    .collect(Collectors.toList());
            }
        }
        if (activities == null) {
            return this.findAll();
        }
        return activities;
    }
    @Override
    public List<Activity> findByEvent(EventType event) {
        return this.service.findByEvent(event);
    }
    @Override
    public List<Activity> findByPseudo(String pseudo) {
        return this.service.findByPseudo(pseudo);
    }

    @GetMapping("/events-types")
    public EventType[] getEventsTypes() {
        return EventType.values();
    }
}
