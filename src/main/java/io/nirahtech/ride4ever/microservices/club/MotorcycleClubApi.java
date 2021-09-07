/******************************************************************
 * Copyright 2021 Ride4Ever
 *
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.club;

import java.util.List;

import io.nirahtech.ride4ever.infrastructure.api.Crud;

interface MotorcycleClubApi extends Crud<String,  MotorcycleClub> {
    List<MotorcycleClub> findAll();
}
