package io.nirahtech.ride4ever.microservices.roadtrip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.nirahtech.ride4ever.core.environment.RoadTrip;

@Repository("roadTripRepository")
public interface RoadTripRepository extends JpaRepository<RoadTrip, Integer> {
    
}
