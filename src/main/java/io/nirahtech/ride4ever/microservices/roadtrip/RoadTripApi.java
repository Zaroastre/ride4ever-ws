/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.roadtrip;

import java.util.List;

import io.nirahtech.ride4ever.infrastructure.api.Crud;

interface RoadTripApi extends Crud<Integer,  RoadTrip> {
    List<RoadTrip> findAll();
}
