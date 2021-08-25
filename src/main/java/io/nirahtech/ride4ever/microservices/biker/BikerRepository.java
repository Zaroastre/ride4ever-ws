package io.nirahtech.ride4ever.microservices.biker;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("bikerRepository")
public interface BikerRepository extends JpaRepository<Biker, Integer> {
    Biker findByEmail(final String email);    
}
