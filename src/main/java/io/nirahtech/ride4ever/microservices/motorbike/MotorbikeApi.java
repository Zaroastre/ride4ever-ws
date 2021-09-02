/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.motorbike;

import java.util.List;

import io.nirahtech.ride4ever.infrastructure.api.Crud;

interface MotorbikeApi extends Crud<Integer,  Motorbike> {
    List<Motorbike> findAll();
    Motorbike findByLicensePlate(final String licensePlate);
    List<Motorbike> findByBikerPseudo(final String pseudo);
}
