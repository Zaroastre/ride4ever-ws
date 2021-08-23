package io.nirahtech.ride4ever.domain.pilot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.nirahtech.ride4ever.core.environment.Biker;

@Repository
public interface PilotRepository extends JpaRepository<Biker, Integer> {
    
}
