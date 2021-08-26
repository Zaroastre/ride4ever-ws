package io.nirahtech.ride4ever.microservices.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("activityService")
public final class ActivityService implements ActivityApi {

    @Autowired
    private ActivityRepository repository;

    @Override
    public Activity create(Activity entity) {
        return this.repository.save(entity);
    }

    @Override
    public Activity read(Integer identifier) {
        Optional<Activity> entity = this.repository.findById(identifier);
        if (entity.isPresent()) {
            return entity.get();
        }
        return null;
    }

    @Override
    public Activity update(Integer identifier, Activity entity) {
        return this.repository.save(entity);
    }

    @Override
    public Activity delete(Integer identifier) {
        Activity entity = this.read(identifier);
        if (entity != null) {
            this.repository.deleteById(entity.getIdentifier());
        }
        return entity;
    }

    @Override
    public List<Activity> findAll() {
        List<Activity> list = new ArrayList<>();
        this.repository.findAll().forEach((item) -> {
            list.add(item);
        });
        return list;
    }

    @Override
    public List<Activity> findByEvent(EventType event) {
        return this.repository.findByEvent(event);
    }

    @Override
    public List<Activity> findByPseudo(String pseudo) {
        return this.repository.findByPseudo(pseudo);
    }

}
