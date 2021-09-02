/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.reservation;

import java.util.List;

import io.nirahtech.ride4ever.infrastructure.api.Crud;

interface ReservationApi extends Crud<Integer,  Reservation> {
    List<Reservation> findAll();
}
