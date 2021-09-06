/******************************************************************
 * Copyright 2021 Ride4Ever
 *
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.motorcycleclub.human;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("bikerRepository")
public interface BikerRepository extends JpaRepository<Biker, Integer> {
    Biker findFirstByEmail(final String email);
    Biker findFirstByPseudo(final String pseudo);

}
