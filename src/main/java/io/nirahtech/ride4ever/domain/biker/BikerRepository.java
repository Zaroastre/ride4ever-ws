package io.nirahtech.ride4ever.domain.biker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.nirahtech.ride4ever.core.environment.Biker;

@Repository("bikerRepository")
public interface BikerRepository extends JpaRepository<Biker, Integer> {
    Biker findByEmail(final String email);    
}
