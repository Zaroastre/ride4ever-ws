/******************************************************************
 * Copyright 2021 Ride4Ever
 * 
 * TO BE DEFINED
 ******************************************************************/
package io.nirahtech.ride4ever.microservices.address;

import java.util.List;

import io.nirahtech.ride4ever.infrastructure.api.Crud;

interface AddressApi extends Crud<Integer,  Address> {
    List<Address> findAll();
    String findWeatherByCoordinates(final double latitude, final double longitude);
}
