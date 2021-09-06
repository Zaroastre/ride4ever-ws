/******************************************************************
 * Copyright 2021 Ride4Ever
 *
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.motorcycleclub.garage;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("motorbikeRepository")
public interface MotorbikeRepository extends CrudRepository<Motorbike, Integer> {
    Motorbike findByLicensePlate(final String licensePlate);

    @Query("SELECT m FROM Motorbike m WHERE m.biker.pseudo=?1")
    List<Motorbike> findByBikerPseudo(final String pseudo);
}
