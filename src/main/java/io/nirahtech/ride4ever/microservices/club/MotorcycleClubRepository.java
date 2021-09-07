/******************************************************************
 * Copyright 2021 Ride4Ever
 *
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.club;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("motorcycleClubRepository")
interface MotorcycleClubRepository extends CrudRepository<MotorcycleClub, String> {

}
