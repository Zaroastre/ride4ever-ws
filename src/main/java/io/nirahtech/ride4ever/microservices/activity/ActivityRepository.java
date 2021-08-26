package io.nirahtech.ride4ever.microservices.activity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("activityRepository")
public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    List<Activity> findByEvent(EventType event); 
}
